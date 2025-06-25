% Load the data
data = [29.7242, 99.9742, 60.9402, 69.853611;
        11.9503, 93.7646, 58.8583, 43.848146;
        76.4815, 46.1927, 82.3581, 118.15744;
        5.0282, 73.3823, 38.6828, 18.653399;
        88.4862, 60.6119, 90.3234, 142.21633;
        68.5463, 11.4105, 83.3835, 77.349556];
    
% Inputs (columns A, B, C) and outputs (column D)
inputs = data(:, 1:3)';
targets = data(:, 4)';

% Define the architecture of the ANN
hiddenLayerSize = 10; % Adjust this value based on experimentation
net = feedforwardnet(hiddenLayerSize);

% Use ReLU for the hidden layer and a linear function for the output
net.layers{1}.transferFcn = 'poslin'; % ReLU activation for hidden layer
net.layers{2}.transferFcn = 'purelin'; % Linear activation for output layer

% Split data into training and testing sets (80% training, 20% testing)
[trainInd,~,testInd] = dividerand(size(inputs,2),0.8,0,0.2);

% Set up division of data for training and testing
net.divideFcn = 'divideind';
net.divideParam.trainInd = trainInd;
net.divideParam.testInd = testInd;

% Train the network
[net, tr] = train(net, inputs, targets);

% Generate the outputs
outputs = net(inputs);
errors = gsubtract(targets, outputs);
performance = perform(net, targets, outputs);

% Retrieve testing and training data
testOutputs = net(inputs(:,testInd));
trainOutputs = net(inputs(:,trainInd));

% Plot training and testing results
figure;
subplot(1,2,1);
plot(targets(trainInd), trainOutputs, 'bo');
title('Training Set Performance');
xlabel('Target');
ylabel('Output');
grid on;

subplot(1,2,2);
plot(targets(testInd), testOutputs, 'ro');
title('Testing Set Performance');
xlabel('Target');
ylabel('Output');
grid on;

% Sensitivity Analysis: Vary one input at a time
variedInput = inputs(:,testInd);
sensitivityResults = [];

for i = 1:3
    % Vary input i while keeping others constant
    for j = 1:length(testInd)
        tempInput = variedInput(:, j);
        tempInput(i) = tempInput(i) * 1.1; % 10% variation
        sensitivityResults(j, i) = net(tempInput);
    end
end

% Plot sensitivity analysis results
figure;
plot(1:length(testInd), sensitivityResults(:,1), 'r', 'LineWidth', 2);
hold on;
plot(1:length(testInd), sensitivityResults(:,2), 'g', 'LineWidth', 2);
plot(1:length(testInd), sensitivityResults(:,3), 'b', 'LineWidth', 2);
title('Sensitivity Analysis');
xlabel('Data Index');
ylabel('ANN Output');
legend('Input 1 varied', 'Input 2 varied', 'Input 3 varied');
grid on;

% Determine if overtraining occurred by checking the error on the test set
trainError = mean((targets(trainInd) - trainOutputs).^2);
testError = mean((targets(testInd) - testOutputs).^2);

fprintf('Training error: %f\n', trainError);
fprintf('Testing error: %f\n', testError);

if abs(trainError - testError) < 0.01
    fprintf('No overtraining occurred.\n');
else
    fprintf('Overtraining may have occurred.\n');
end

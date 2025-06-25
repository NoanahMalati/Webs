% Load the input data (3 features) and target data (output)
data = [29.7242, 99.9742, 60.9402, 69.853611;
        11.9503, 93.7646, 58.8583, 43.848146;
        76.4815, 46.1927, 82.3581, 118.15744;
        5.0282, 73.3823, 38.6828, 18.653399;
        88.4862, 60.6119, 90.3234, 142.21633;
        68.5463, 11.4105, 83.3835, 77.349556];

% Define inputs and targets
inputs = data(:, 1:3)';  % Inputs (3 features, transpose for ANN format)
targets = data(:, 4)';   % Targets (Transpose for ANN format)
% Define and configure a feedforward neural network with 10 hidden neurons
hiddenLayerSize = 15;
net = feedforwardnet(hiddenLayerSize);

% Divide the data into 60% training, 20% validation, and 20% testing
net.divideParam.trainRatio = 0.6;
net.divideParam.valRatio = 0.2;
net.divideParam.testRatio = 0.2;

% Train the neural network
[net, tr] = train(net, inputs, targets);
% Sensitivity analysis: Vary one input at a time by 10%
sensitivityResultsInput1 = [];
sensitivityResultsInput2 = [];
sensitivityResultsInput3 = [];

% Retrieve test indices
testInd = tr.testInd;

% Loop through each test sample and vary each input by 10%
for i = 1:length(testInd)
    tempInput = inputs(:, testInd(i));
    
    % Vary Input 1
    tempInput1 = tempInput;
    tempInput1(1) = tempInput1(1) * 1.1;  % Increase by 10%
    sensitivityResultsInput1(i) = net(tempInput1);
    
    % Vary Input 2
    tempInput2 = tempInput;
    tempInput2(2) = tempInput2(2) * 1.1;  % Increase by 10%
    sensitivityResultsInput2(i) = net(tempInput2);
    
    % Vary Input 3
    tempInput3 = tempInput;
    tempInput3(3) = tempInput3(3) * 1.1;  % Increase by 10%
    sensitivityResultsInput3(i) = net(tempInput3);
end
% Plot sensitivity results
figure;
hold on;
plot(1:length(testInd), sensitivityResultsInput1, 'r', 'LineWidth', 2);
plot(1:length(testInd), sensitivityResultsInput2, 'g', 'LineWidth', 2);
plot(1:length(testInd), sensitivityResultsInput3, 'b', 'LineWidth', 2);
title('Sensitivity Analysis for Input Variation by 10%');
xlabel('Test Data Index');
ylabel('ANN Output');
legend('Input 1 Varied', 'Input 2 Varied', 'Input 3 Varied');
grid on;
hold off;

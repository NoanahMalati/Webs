% Set the random seed for reproducibility
rng(0);

% Generate data for addition
X1 = rand(100, 1) * 10;  % First random number between 1 and 10
X2 = rand(100, 1) * 10;  % Second random number between 1 and 10
Y = X1 + X2;             % Target output (sum of the two numbers)
X = [X1 X2];

% Create a simple feedforward neural network
net = feedforwardnet([10]);  % 1 hidden layer with 10 neurons

% Set data division: 70% training, 15% validation, 15% testing
net.divideParam.trainRatio = 0.7;  % 70% of data for training
net.divideParam.valRatio = 0.15;   % 15% of data for validation
net.divideParam.testRatio = 0.15;  % 15% of data for testing

% Train the network
[net, tr] = train(net, X', Y');

% Plot training, validation, and test performance
figure;
plotperform(tr);
title('Training, Validation, and Test Performance');
% Set the random seed for reproducibility
rng(0);

% Step 1: Generate clean data for addition
X1 = rand(100, 1) * 10;  % First random number between 1 and 10
X2 = rand(100, 1) * 10;  % Second random number between 1 and 10
Y = X1 + X2;             % Target output (sum of the two numbers)
X = [X1 X2];             % Concatenate X1 and X2 into input matrix

% Step 2: Add Gaussian noise to the input data
noise = randn(100, 2) * 0.5;  % Gaussian noise (mean 0, std 0.5)
X_noisy = X + noise;          % Add noise to input data

% Plot the noisy data (optional, for visualization)
figure;
scatter3(X_noisy(:,1), X_noisy(:,2), Y);
xlabel('X1 (with noise)');
ylabel('X2 (with noise)');
zlabel('Sum (Y)');
title('Input Data with Noise');
grid on;

% Step 3: Create a simple feedforward neural network
net = feedforwardnet([10]);  % 1 hidden layer with 10 neurons

% Step 4: Set data division (70% training, 15% validation, 15% testing)
net.divideParam.trainRatio = 0.7;  % 70% of data for training
net.divideParam.valRatio = 0.15;   % 15% of data for validation
net.divideParam.testRatio = 0.15;  % 15% of data for testing

% Step 5: Train the network with noisy data
[net, tr] = train(net, X_noisy', Y');  % Transpose the data to match the function format

% Step 6: Plot training, validation, and test performance curves
figure;
plotperform(tr);
title('Training, Validation, and Test Performance (Noisy Data)');

% Step 7: Evaluate the trained network on the noisy data
Y_pred = net(X_noisy');  % Predict output using the trained network

% Step 8: Plot the predictions vs actual values to compare performance
figure;
plot(1:length(Y), Y, 'b', 1:length(Y), Y_pred, 'r--');
legend('Actual', 'Predicted');
title('ANN Prediction vs. Actual for Addition with Noisy Data');
xlabel('Sample Number');
ylabel('Sum (Y)');

% Step 9: Plot the training state over time (optional)
figure;
plottrainstate(tr);
title('Training State Over Time (Noisy Data)');
% Set the random seed for reproducibility
rng(0);

% Generate clean data for addition
X1 = rand(100, 1) * 10;  % First random number between 1 and 10
X2 = rand(100, 1) * 10;  % Second random number between 1 and 10
Y = X1 + X2;             % Target output (sum of the two numbers)
X = [X1 X2];

% Create a neural network with 1 hidden layer, 5 neurons
net = feedforwardnet([5]);  % 1 hidden layer with 5 neurons

% Set data division (70% training, 15% validation, 15% testing)
net.divideParam.trainRatio = 0.7;  
net.divideParam.valRatio = 0.15;   
net.divideParam.testRatio = 0.15;  

% Train the network
[net, tr] = train(net, X', Y');

% Plot training, validation, and test performance
figure;
plotperform(tr);
title('Performance: 1 Hidden Layer with 5 Neurons');
% Create a neural network with 1 hidden layer, 20 neurons
net = feedforwardnet([20]);  % 1 hidden layer with 20 neurons

% Train the network
[net, tr] = train(net, X', Y');

% Plot training, validation, and test performance
figure;
plotperform(tr);
title('Performance: 1 Hidden Layer with 20 Neurons');
% Create a neural network with 2 hidden layers, 10 neurons each
net = feedforwardnet([10, 10]);  % 2 hidden layers with 10 neurons each

% Train the network
[net, tr] = train(net, X', Y');

% Plot training, validation, and test performance
figure;
plotperform(tr);
title('Performance: 2 Hidden Layers with 10 Neurons Each');
ANN6
Training Error: 32358.727302
Validation Error: 1.827084
Testing Error: 1330.792701
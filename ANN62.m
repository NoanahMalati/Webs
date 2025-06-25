% Split into inputs (A, B, C) and outputs (D)
inputs = data(:, 1:3)';  % Transpose to get proper input matrix dimensions for ANN
targets = data(:, 4)';   % Transpose for the same reason

% Split into training and testing sets (90/10 split)
train_ratio = 0.8;
num_train = round(train_ratio * size(data, 1));

train_inputs = inputs(:, 1:num_train);
train_targets = targets(1:num_train);

test_inputs = inputs(:, num_train+1:end);
test_targets = targets(num_train+1:end);

% Create the ANN with one hidden layer of 5 neurons
hidden_layer_size = 4;
net = feedforwardnet(hidden_layer_size);

% Train the network
[net, tr] = train(net, train_inputs, train_targets);

% Get the network's performance on training and testing sets
train_outputs = net(train_inputs);
test_outputs = net(test_inputs);

train_perf = perform(net, train_targets, train_outputs);
test_perf = perform(net, test_targets, test_outputs);

% Display performance
fprintf('Training Performance: %f\n', train_perf);
fprintf('Testing Performance: %f\n', test_perf);

% Plot training set predictions
figure;
plot(train_targets, 'bo');
hold on;
plot(train_outputs, 'r*');
title('Training Set: Actual vs Predicted');
legend('Actual', 'Predicted');
xlabel('Data Point');
ylabel('Output Value');
hold off;

% Plot testing set predictions
figure;
plot(test_targets, 'bo');
hold on;
plot(test_outputs, 'r*');
title('Testing Set: Actual vs Predicted');
legend('Actual', 'Predicted');
xlabel('Data Point');
ylabel('Output Value');
hold off;

% Sensitivity Analysis

% Vary Input A
A_values = linspace(min(train_inputs(1, :)), max(train_inputs(1, :)), 100);
mean_B = mean(train_inputs(2, :));
mean_C = mean(train_inputs(3, :));

% Use mean values for B and C, vary A
B_values = mean_B * ones(size(A_values));
C_values = mean_C * ones(size(A_values));
inputs_varied_A = [A_values; B_values; C_values];
outputs_varied_A = net(inputs_varied_A);

% Vary Input B
B_values = linspace(min(train_inputs(2, :)), max(train_inputs(2, :)), 100);
A_values_B = mean(train_inputs(1, :)) * ones(size(B_values));
C_values_B = mean_C * ones(size(B_values));
inputs_varied_B = [A_values_B; B_values; C_values_B];
outputs_varied_B = net(inputs_varied_B);

% Vary Input C
C_values = linspace(min(train_inputs(3, :)), max(train_inputs(3, :)), 100);
A_values_C = mean(train_inputs(1, :)) * ones(size(C_values));
B_values_C = mean_B * ones(size(C_values));
inputs_varied_C = [A_values_C; B_values_C; C_values];
outputs_varied_C = net(inputs_varied_C);

% Plot sensitivity analysis

% Plot varying A
figure;
plot(A_values, outputs_varied_A, 'r');
title('Sensitivity Analysis: Varying Input A');
xlabel('Input A Value');
ylabel('Output D');
legend('Varying A');
hold off;

% Plot varying B
figure;
plot(B_values, outputs_varied_B, 'g');
title('Sensitivity Analysis: Varying Input B');
xlabel('Input B Value');
ylabel('Output D');
legend('Varying B');
hold off;

% Plot varying C
figure;
plot(C_values, outputs_varied_C, 'b');
title('Sensitivity Analysis: Varying Input C');
xlabel('Input C Value');
ylabel('Output D');
legend('Varying C');
hold off;

% Split into inputs (A, B, C)
inputs = data(:, 1:3)';  % Transpose to get proper input matrix dimensions for ANN

% Extract A, B, and C for plotting
A = inputs(1, :);
B = inputs(2, :);
C = inputs(3, :);

% Plot A against B
figure;
scatter(A, B, 'r');
title('Plot of A against B');
xlabel('Input A');
ylabel('Input B');
grid on;

% Plot B against C
figure;
scatter(B, C, 'g');
title('Plot of B against C');
xlabel('Input B');
ylabel('Input C');
grid on;

% Plot A against C
figure;
scatter(A, C, 'b');
title('Plot of A against C');
xlabel('Input A');
ylabel('Input C');
grid on;

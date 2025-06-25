% Clear workspace and command window
clear;
clc;

% Load dataset (replace 'fuel_data.csv' with your actual dataset)
% Assume the dataset contains columns such as 'FuelBurn', 'BatteryUsage', 'Altitude', 'Speed', 'Thrust', etc.
data = readtable('fuelsaveddata.xlsx');

% Extract inputs and output
% Assuming 'FuelSaved' is the target variable and other columns are inputs
inputs = data{:, {'Sealeveltakeoffpowersplit', 'Cruisetargetthrustsplit', 'Systemvoltage', 'Climbmotorpowercode', 'Batteryspecificenergy', 'Electricmotorspecificpower', 'Inverterspecificpower', 'Electricmotorefficiency', 'Inverterefficiency', 'FuelSavings'}}; % Adjust variable names accordingly
fuel_saved = data.FuelSaved;

% Normalize the inputs to remove the effect of scale (optional but recommended)
inputs_norm = normalize(inputs);

% Fit a multiple linear regression model to predict fuel saved
model = fitlm(inputs_norm, fuel_saved);

% Display the model summary
disp('Regression Model Summary:');
disp(model);

% Get the coefficients of the model
coefficients = model.Coefficients.Estimate;

% Interpret the impact of each input
disp('Coefficients of each input variable:');
for i = 2:length(coefficients)
    fprintf('Impact of %s: %.4f\n', model.PredictorNames{i-1}, coefficients(i));
end

% Optional: Plot the coefficients to visualize the input importance
figure;
bar(coefficients(2:end));
set(gca, 'XTickLabel', model.PredictorNames);
xlabel('Input Variables');
ylabel('Coefficient Magnitude');
title('Impact of Each Input on Fuel Saved');
grid on;

% Perform sensitivity analysis
% Calculate predicted values and residuals
predicted_fuel_saved = predict(model, inputs_norm);
residuals = fuel_saved - predicted_fuel_saved;

% Plot predicted vs actual fuel saved
figure;
scatter(fuel_saved, predicted_fuel_saved);
xlabel('Actual Fuel Saved');
ylabel('Predicted Fuel Saved');
title('Predicted vs Actual Fuel Saved');
grid on;

% Optional: Calculate R-squared value to assess model fit
r_squared = model.Rsquared.Ordinary;
fprintf('R-squared: %.4f\n', r_squared);

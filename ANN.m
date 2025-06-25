p = [1,3;9,2] %input matrix
t = [10,5]    %Target vector

net = fitnet(12);
net = fitnet ([12,6]);
net = fitnet (12, 'traingdx');

help fitnet

net.divideParam.trainRatio = 70/100;   % 70% of data for training
net.divideParam.valRatio = 15/100;     % 15% for validation
net.divideParam.testRatio = 15/100;    % 15% for testing

net = train(net, p, t);           % Basic training command
[net, tr] = train(net, p, t);     % Training command that also returns training record `tr`

output = sim(net, p2);  % Runs the network with new input data `p2`
output = net(p2);       % An equivalent way to run the network

y = sim(net, p);              % First, run the network with your training input `p` to get the output `y`
mse = perform(net, y, t);     % Then, calculate the MSE between the predicted output `y` and the target `t`

net = init(net);  % Reset the network weights to their initial state


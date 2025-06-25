clear all
close all

vcdata = readtable("vcdata.csv");
f_theory = logspace(3, 6, 1000);
omega_theory = 2 * pi * f_theory;
L=0.047;
R=2000;
C=100e-12;
Zin_theory = R + j*omega_theory*L + 1./(j*omega_theory*C);
VC_VS_ratio_theory= 1./(j*omega_theory*C)./Zin_theory;

figure(1)
subplot(211);
semilogx(vcdata.Frequency, vcdata.Amplitude/.5, 'k*','LineWidth',2);
hold on
semilogx(f_theory, abs(VC_VS_ratio_theory), 'r', 'LineWidth', 2);
xlabel('Frequency (Hz)');
ylabel('|V_C / V_S|');
title('(|V_C / V_S|) vs. Frequency');


subplot(212);
semilogx(vcdata.Frequency, vcdata.Phase/360*2*pi, 'k*','LineWidth',2);
hold on;
semilogx(f_theory, angle(VC_VS_ratio_theory), 'r', 'LineWidth', 2);
ylim([-pi pi]);
xlabel('Frequency (Hz)');
ylabel('Angle (Radians)');
title('Angle vs. Frequency');
hold off





VRdata = readtable("VRdata.csv");
VR_VS_ratio_theory= R./Zin_theory;

figure(2)
subplot(211);
semilogx(VRdata.Frequency, VRdata.Amplitude/.5, 'k*','LineWidth',2);
hold on
semilogx(f_theory, abs(VR_VS_ratio_theory), 'r', 'LineWidth', 2);
xlabel('Frequency (Hz)');
ylabel('|V_R / V_S|');
title('(|V_R / V_S|) vs. Frequency');

subplot(212);
semilogx(VRdata.Frequency, VRdata.Phase/360*2*pi, 'k*','LineWidth',2);
hold on;
semilogx(f_theory, angle(VR_VS_ratio_theory), 'r', 'LineWidth', 2);
ylim([-pi pi]);
xlabel('Frequency (Hz)');
ylabel('Angle (Radians)');
title('Angle vs. Frequency');
hold off





VLCdata = readtable("VLCdata.csv");
VLC_VS_ratio_theory=(j*omega_theory*L + 1./(j*omega_theory*C))./Zin_theory

figure(3)
subplot(211);
semilogx(VLCdata.Frequency, VLCdata.Amplitude/.5, 'k*','LineWidth',2);
hold on
semilogx(f_theory, abs(VLC_VS_ratio_theory), 'r', 'LineWidth', 2);
xlabel('Frequency (Hz)');
ylabel('|V_L_C / V_S|');
title('(|V_L_C/ V_S|) vs. Frequency');

subplot(212);
semilogx(VLCdata.Frequency, VLCdata.Phase/360*2*pi, 'k*','LineWidth',2);
hold on;
semilogx(f_theory, angle(VLC_VS_ratio_theory), 'r', 'LineWidth', 2);
ylim([-pi pi]);
xlabel('Frequency (Hz)');
ylabel('Angle (Radians)');
title('Angle vs. Frequency');
hold off





VLdata = readtable("VLdata.csv");
VL_VS_ratio_theory=(j*omega_theory*L)./Zin_theory;

figure(4)
subplot(211);
semilogx(VLdata.Frequency, VLdata.Amplitude/.5, 'k*','LineWidth',2);
hold on
semilogx(f_theory, abs(VL_VS_ratio_theory), 'r', 'LineWidth', 2);
xlabel('Frequency (Hz)');
ylabel('|V_L / V_S|');
title('(|V_L/ V_S|) vs. Frequency');

subplot(212);
semilogx(VLdata.Frequency, VLdata.Phase/360*2*pi, 'k*','LineWidth',2);
hold on;
semilogx(f_theory, angle(VL_VS_ratio_theory), 'r', 'LineWidth', 2);
ylim([-pi pi]);
xlabel('Frequency (Hz)');
ylabel('Angle (Radians)');
title('Angle vs. Frequency');
hold off



Vs_amplitude = 500; % Source voltage amplitude in mV
I = Vs_amplitude./ Zin_theory;

figure (5);
semilogx(f_theory, I, 'b', 'LineWidth', 2); % Logarithmic scale for frequency axis
xlabel('Frequency (Hz)');
ylabel('Current Magnitude (mA)');
title('Current vs. Frequency in Series RLC Circuit');
grid on;


R2 = 50000; % New resistance value (ohms)
Zin_theory2 = R2 + j*omega_theory*L + 1./(j*omega_theory*C);
VL_VS_ratio_theory2=(j*omega_theory*L)./Zin_theory2;


figure(6)
subplot(211);
semilogx(f_theory, abs(VL_VS_ratio_theory), 'r', 'LineWidth', 2);
hold on
semilogx(f_theory, abs(VL_VS_ratio_theory2), 'b', 'LineWidth', 2);
xlabel('Frequency (Hz)');
ylabel('|V_L / V_S|');
title('(|V_L / V_S|) vs. Frequency');


subplot(212);
semilogx(f_theory, angle(VL_VS_ratio_theory), 'r', 'LineWidth', 2);
hold on
semilogx(f_theory, angle(VL_VS_ratio_theory2), 'b', 'LineWidth', 2);
ylim([-pi pi]);
xlabel('Frequency (Hz)');
ylabel('Angle (Radians)');
title('Angle vs. Frequency');
hold off
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


V


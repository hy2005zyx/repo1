@echo off
echo.
@rem win7:interface=��������  win8:interface=��̫��_2

echo    ------------------��ѡ��ϵͳ------------------
echo    1    win7 ��������
echo    2    win8 win10 ��̫��_2
echo.
set /p mysystem=��ѡ������ϵͳ��
echo.

if "%mysystem%"=="1" goto win7
if "%mysystem%"=="2" goto win8

:win7
echo.
echo    ------------------��ѡ�������ص�------------------
echo    1     2513��218.196.14.244��
echo    2     2514��192.168.14.244��
echo    3     2516��192.168.15.244��
echo    4     2520��192.168.19.244��
echo    5     2522��192.168.20.244��
echo    6     2507��218.196.14.244��
echo    0     �Զ���ȡip��ַ 
echo.
set /p input=��ѡ�����������ص㣺
echo.

if "%input%"=="1" goto ip11
if "%input%"=="2" goto ip12
if "%input%"=="3" goto ip13
if "%input%"=="4" goto ip14
if "%input%"=="5" goto ip15
if "%input%"=="6" goto ip16
if "%input%"=="0" goto ip17
:ip11
echo 2513�칫��IP��ַ���ÿ�ʼ
netsh interface ip set address �������� static 218.196.14.244 255.255.255.0 218.196.14.254 0
netsh interface ip set dns �������� static 59.51.78.210 PRIMARY
netsh interface ip add dns �������� 8.8.8.8 index=2
@echo �������,���Զ��˳�
exit

:ip12
echo 2514�칫��IP��ַ���ÿ�ʼ
netsh interface ip set address �������� static 192.168.14.244 255.255.255.0 192.168.14.254 0
netsh interface ip set dns �������� static 59.51.78.210 PRIMARY
netsh interface ip add dns �������� 8.8.8.8 index=2
@echo �������,���Զ��˳�
exit

:ip13
echo 2516�칫��IP��ַ���ÿ�ʼ
netsh interface ip set address �������� static 192.168.15.244 255.255.255.0 192.168.15.254 0
netsh interface ip set dns �������� static 59.51.78.210 PRIMARY
netsh interface ip add dns �������� 8.8.8.8 index=2
@echo �������,���Զ��˳�
exit

:ip14
echo 2520�칫��IP��ַ���ÿ�ʼ
netsh interface ip set address �������� static 192.168.19.244 255.255.255.0 192.168.19.254 0
netsh interface ip set dns �������� static 59.51.78.210 PRIMARY
netsh interface ip add dns �������� 8.8.8.8 index=2
@echo �������,���Զ��˳�
exit

:ip15
echo 2522�칫��IP��ַ���ÿ�ʼ
netsh interface ip set address �������� static 192.168.20.244 255.255.255.0 192.168.20.254 0
netsh interface ip set dns �������� static 59.51.78.210 PRIMARY
netsh interface ip add dns �������� 8.8.8.8 index=2
@echo �������,���Զ��˳�
exit
:ip16
echo 2507�칫��IP��ַ���ÿ�ʼ
netsh interface ip set address �������� static 218.196.14.244 255.255.255.0 218.196.14.254 0
netsh interface ip set dns �������� static 59.51.78.210 PRIMARY
netsh interface ip add dns �������� 8.8.8.8 index=2
@echo �������,���Զ��˳�
exit
:ip17
echo  �Զ���ȡIP��ַ���ÿ�ʼ
netsh interface ip set address "��������" source = dhcp 
netsh interface ip set dns name = "��������" source = dhcp
@echo �������,���Զ��˳�
exit

:win8
echo.
echo    ------------------��ѡ�������ص�------------------
echo    1     2513��218.196.14.244��
echo    2     2514��192.168.14.244��
echo    3     2516��192.168.15.244��
echo    4     2520��192.168.19.244��
echo    5     2522��192.168.20.244��
echo    6     2507��218.196.14.244��
echo    0     �Զ���ȡip��ַ     
:again
echo.
set /p input=������Ҫ���õ������ص㣺
echo.

if "%input%"=="1" goto ip1
if "%input%"=="2" goto ip2
if "%input%"=="3" goto ip3
if "%input%"=="4" goto ip4
if "%input%"=="5" goto ip5
if "%input%"=="6" goto ip6
if "%input%"=="0" goto ip7
:ip1
echo 2513�칫��IP��ַ���ÿ�ʼ
netsh interface ip set address ��̫��_2 static 218.196.14.244 255.255.255.0 218.196.14.254 0
netsh interface ip set dns ��̫��_2 static 59.51.78.210 PRIMARY
netsh interface ip add dns ��̫��_2 8.8.8.8 index=2
@echo �������,���Զ��˳�
exit

:ip2
echo 2514�칫��IP��ַ���ÿ�ʼ
netsh interface ip set address ��̫��_2 static 192.168.14.244 255.255.255.0 192.168.14.254 0
netsh interface ip set dns ��̫��_2 static 59.51.78.210 PRIMARY
netsh interface ip add dns ��̫��_2 8.8.8.8 index=2
@echo �������,���Զ��˳�
exit

:ip3
echo 2516�칫��IP��ַ���ÿ�ʼ
netsh interface ip set address ��̫��_2 static 192.168.15.244 255.255.255.0 192.168.15.254 0
netsh interface ip set dns ��̫��_2 static 59.51.78.210 PRIMARY
netsh interface ip add dns ��̫��_2 8.8.8.8 index=2
@echo �������,���Զ��˳�
exit

:ip4
echo 2520�칫��IP��ַ���ÿ�ʼ
netsh interface ip set address ��̫��_2 static 192.168.19.244 255.255.255.0 192.168.19.254 0
netsh interface ip set dns ��̫��_2 static 59.51.78.210 PRIMARY
netsh interface ip add dns ��̫��_2 8.8.8.8 index=2
@echo �������,���Զ��˳�
exit

:ip5
echo 2522�칫��IP��ַ���ÿ�ʼ
netsh interface ip set address ��̫��_2 static 192.168.20.244 255.255.255.0 192.168.20.254 0
netsh interface ip set dns ��̫��_2 static 59.51.78.210 PRIMARY
netsh interface ip add dns ��̫��_2 8.8.8.8 index=2
@echo �������,���Զ��˳�
exit
:ip6
echo 2507�칫��IP��ַ���ÿ�ʼ
netsh interface ip set address ��̫��_2 static 218.196.14.244 255.255.255.0 218.196.14.254 0
netsh interface ip set dns ��̫��_2 static 59.51.78.210 PRIMARY
netsh interface ip add dns ��̫��_2 8.8.8.8 index=2
@echo �������,���Զ��˳�
exit
:ip7
echo  �Զ���ȡIP��ַ���ÿ�ʼ
netsh interface ip set address ��̫��_2 source = dhcp 
netsh interface ip set dns ��̫��_2 source = dhcp
@echo �������,���Զ��˳�
exit

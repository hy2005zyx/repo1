@echo off
echo.
@rem win7:interface=��������  win8:interface=��̫��

echo    ------------------��ѡ��ϵͳ------------------
echo    1    win7 ��������
echo    2    win8 win10 ��̫��
echo.
set /p mysystem=��ѡ������ϵͳ��
echo.

if "%mysystem%"=="1" goto win7
if "%mysystem%"=="2" goto win8

:win7
echo.
echo    ------------------��ѡ�������ص�------------------
echo    2513     2513����ʹ�õ�IP
echo    2514     2514����ʹ�õ�IP
echo    2516     2516����ʹ�õ�IP
echo    2520     2520����ʹ�õ�IP
echo    2522     2522����ʹ�õ�IP
echo    2507     2507����ʹ�õ�IP
echo    666     �Զ���ȡip��ַ��666��
echo.
set /p input=��ѡ�����������ص㣺
echo.

if "%input%"=="2513" goto ip11
if "%input%"=="2514" goto ip12
if "%input%"=="2516" goto ip13
if "%input%"=="2520" goto ip14
if "%input%"=="2522" goto ip15
if "%input%"=="2507" goto ip16
if "%input%"=="666" goto ip17
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
netsh interface ip set address ��̫�� static 192.168.19.244 255.255.255.0 192.168.19.254 0
netsh interface ip set dns ��̫�� static 59.51.78.210 PRIMARY
netsh interface ip add dns ��̫�� 8.8.8.8 index=2
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
echo    2513     2513����ʹ�õ�IP
echo    2514     2514����ʹ�õ�IP
echo    2516     2516����ʹ�õ�IP
echo    2520     2520����ʹ�õ�IP
echo    2522     2522����ʹ�õ�IP
echo    2507     2507����ʹ�õ�IP
echo    666     �Զ���ȡip��ַ��666��     
:again
echo.
set /p input=������Ҫ���õ������ص㣺
echo.

if "%input%"=="2513" goto ip1
if "%input%"=="2514" goto ip2
if "%input%"=="2516" goto ip3
if "%input%"=="2520" goto ip4
if "%input%"=="2522" goto ip5
if "%input%"=="2507" goto ip6
if "%input%"=="666" goto ip7
:ip1
echo 2513�칫��IP��ַ���ÿ�ʼ
netsh interface ip set address ��̫�� static 218.196.14.108 255.255.255.0 218.196.14.254 0
netsh interface ip set dns ��̫�� static 59.51.78.210 PRIMARY
netsh interface ip add dns ��̫�� 8.8.8.8 index=2
@echo �������,���Զ��˳�
exit

:ip2
echo 2514�칫��IP��ַ���ÿ�ʼ
netsh interface ip set address ��̫�� static 192.168.19.108 255.255.255.0 192.168.19.254 0
netsh interface ip set dns ��̫�� static 59.51.78.210 PRIMARY
netsh interface ip add dns ��̫�� 8.8.8.8 index=2
@echo �������,���Զ��˳�
exit

:ip3
echo 2516�칫��IP��ַ���ÿ�ʼ
netsh interface ip set address ��̫�� static 192.168.15.108 255.255.255.0 192.168.15.254 0
netsh interface ip set dns ��̫�� static 59.51.78.210 PRIMARY
netsh interface ip add dns ��̫�� 8.8.8.8 index=2
@echo �������,���Զ��˳�
exit

:ip4
echo 2520�칫��IP��ַ���ÿ�ʼ
netsh interface ip set address ��̫�� static 192.168.20.108 255.255.255.0 192.168.20.254 0
netsh interface ip set dns ��̫�� static 59.51.78.210 PRIMARY
netsh interface ip add dns ��̫�� 8.8.8.8 index=2
@echo �������,���Զ��˳�
exit

:ip5
echo 2522�칫��IP��ַ���ÿ�ʼ
netsh interface ip set address ��̫�� static 192.168.20.108 255.255.255.0 192.168.20.254 0
netsh interface ip set dns ��̫�� static 59.51.78.210 PRIMARY
netsh interface ip add dns ��̫�� 8.8.8.8 index=2
@echo �������,���Զ��˳�
exit
:ip6
echo 2507�칫��IP��ַ���ÿ�ʼ
netsh interface ip set address ��̫�� static 218.196.14.244 255.255.255.0 218.196.14.254 0
netsh interface ip set dns ��̫�� static 59.51.78.210 PRIMARY
netsh interface ip add dns ��̫�� 8.8.8.8 index=2
@echo �������,���Զ��˳�
exit
:ip7
echo  �Զ���ȡIP��ַ���ÿ�ʼ
netsh interface ip set address "��̫��" source = dhcp 
netsh interface ip set dns name = "��̫��" source = dhcp
@echo �������,���Զ��˳�
exit

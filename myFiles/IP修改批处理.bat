@echo off
echo.
@rem win7:interface=本地连接  win8:interface=以太网

echo    ------------------请选择系统------------------
echo    1    win7 本地连接
echo    2    win8 win10 以太网
echo.
set /p mysystem=请选择您的系统：
echo.

if "%mysystem%"=="1" goto win7
if "%mysystem%"=="2" goto win8

:win7
echo.
echo    ------------------请选择上网地点------------------
echo    2513     2513上网使用的IP
echo    2514     2514上网使用的IP
echo    2516     2516上网使用的IP
echo    2520     2520上网使用的IP
echo    2522     2522上网使用的IP
echo    2507     2507上网使用的IP
echo    666     自动获取ip地址（666）
echo.
set /p input=请选择您的上网地点：
echo.

if "%input%"=="2513" goto ip11
if "%input%"=="2514" goto ip12
if "%input%"=="2516" goto ip13
if "%input%"=="2520" goto ip14
if "%input%"=="2522" goto ip15
if "%input%"=="2507" goto ip16
if "%input%"=="666" goto ip17
:ip11
echo 2513办公室IP地址设置开始
netsh interface ip set address 本地连接 static 218.196.14.244 255.255.255.0 218.196.14.254 0
netsh interface ip set dns 本地连接 static 59.51.78.210 PRIMARY
netsh interface ip add dns 本地连接 8.8.8.8 index=2
@echo 设置完毕,将自动退出
exit

:ip12
echo 2514办公室IP地址设置开始
netsh interface ip set address 本地连接 static 192.168.14.244 255.255.255.0 192.168.14.254 0
netsh interface ip set dns 本地连接 static 59.51.78.210 PRIMARY
netsh interface ip add dns 本地连接 8.8.8.8 index=2
@echo 设置完毕,将自动退出
exit

:ip13
echo 2516办公室IP地址设置开始
netsh interface ip set address 本地连接 static 192.168.15.244 255.255.255.0 192.168.15.254 0
netsh interface ip set dns 本地连接 static 59.51.78.210 PRIMARY
netsh interface ip add dns 本地连接 8.8.8.8 index=2
@echo 设置完毕,将自动退出
exit

:ip14
echo 2520办公室IP地址设置开始
netsh interface ip set address 以太网 static 192.168.19.244 255.255.255.0 192.168.19.254 0
netsh interface ip set dns 以太网 static 59.51.78.210 PRIMARY
netsh interface ip add dns 以太网 8.8.8.8 index=2
@echo 设置完毕,将自动退出
exit

:ip15
echo 2522办公室IP地址设置开始
netsh interface ip set address 本地连接 static 192.168.20.244 255.255.255.0 192.168.20.254 0
netsh interface ip set dns 本地连接 static 59.51.78.210 PRIMARY
netsh interface ip add dns 本地连接 8.8.8.8 index=2
@echo 设置完毕,将自动退出
exit
:ip16
echo 2507办公室IP地址设置开始
netsh interface ip set address 本地连接 static 218.196.14.244 255.255.255.0 218.196.14.254 0
netsh interface ip set dns 本地连接 static 59.51.78.210 PRIMARY
netsh interface ip add dns 本地连接 8.8.8.8 index=2
@echo 设置完毕,将自动退出
exit
:ip17
echo  自动获取IP地址设置开始
netsh interface ip set address "本地连接" source = dhcp 
netsh interface ip set dns name = "本地连接" source = dhcp
@echo 设置完毕,将自动退出
exit

:win8
echo.
echo    ------------------请选择上网地点------------------
echo    2513     2513上网使用的IP
echo    2514     2514上网使用的IP
echo    2516     2516上网使用的IP
echo    2520     2520上网使用的IP
echo    2522     2522上网使用的IP
echo    2507     2507上网使用的IP
echo    666     自动获取ip地址（666）     
:again
echo.
set /p input=请输入要设置的上网地点：
echo.

if "%input%"=="2513" goto ip1
if "%input%"=="2514" goto ip2
if "%input%"=="2516" goto ip3
if "%input%"=="2520" goto ip4
if "%input%"=="2522" goto ip5
if "%input%"=="2507" goto ip6
if "%input%"=="666" goto ip7
:ip1
echo 2513办公室IP地址设置开始
netsh interface ip set address 以太网 static 218.196.14.108 255.255.255.0 218.196.14.254 0
netsh interface ip set dns 以太网 static 59.51.78.210 PRIMARY
netsh interface ip add dns 以太网 8.8.8.8 index=2
@echo 设置完毕,将自动退出
exit

:ip2
echo 2514办公室IP地址设置开始
netsh interface ip set address 以太网 static 192.168.19.108 255.255.255.0 192.168.19.254 0
netsh interface ip set dns 以太网 static 59.51.78.210 PRIMARY
netsh interface ip add dns 以太网 8.8.8.8 index=2
@echo 设置完毕,将自动退出
exit

:ip3
echo 2516办公室IP地址设置开始
netsh interface ip set address 以太网 static 192.168.15.108 255.255.255.0 192.168.15.254 0
netsh interface ip set dns 以太网 static 59.51.78.210 PRIMARY
netsh interface ip add dns 以太网 8.8.8.8 index=2
@echo 设置完毕,将自动退出
exit

:ip4
echo 2520办公室IP地址设置开始
netsh interface ip set address 以太网 static 192.168.20.108 255.255.255.0 192.168.20.254 0
netsh interface ip set dns 以太网 static 59.51.78.210 PRIMARY
netsh interface ip add dns 以太网 8.8.8.8 index=2
@echo 设置完毕,将自动退出
exit

:ip5
echo 2522办公室IP地址设置开始
netsh interface ip set address 以太网 static 192.168.20.108 255.255.255.0 192.168.20.254 0
netsh interface ip set dns 以太网 static 59.51.78.210 PRIMARY
netsh interface ip add dns 以太网 8.8.8.8 index=2
@echo 设置完毕,将自动退出
exit
:ip6
echo 2507办公室IP地址设置开始
netsh interface ip set address 以太网 static 218.196.14.244 255.255.255.0 218.196.14.254 0
netsh interface ip set dns 以太网 static 59.51.78.210 PRIMARY
netsh interface ip add dns 以太网 8.8.8.8 index=2
@echo 设置完毕,将自动退出
exit
:ip7
echo  自动获取IP地址设置开始
netsh interface ip set address "以太网" source = dhcp 
netsh interface ip set dns name = "以太网" source = dhcp
@echo 设置完毕,将自动退出
exit

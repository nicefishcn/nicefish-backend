
set path1=..\mybatis-generator-lombok-plugin
set url1="https://github.com/kimmking/mybatis-generator-lombok-plugin"
if exist %path1% (
   rd /s/q %path1%
)

echo add %url1%
git clone %url1% %path1%
cd %path1%
call install.bat

set path1=..\mybatis-generator-core
set url1="https://git.oschina.net/kimmking/mybatis-generator-core.git"
if exist %path1% (
   rd /s/q %path1%
)

echo add %url1%
git clone %url1% %path1%
cd %path1%
call install.bat

cd ..\nicefish-backend

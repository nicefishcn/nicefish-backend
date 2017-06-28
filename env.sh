
function cloneAndInstall() {
  if [ -d "$1" ]; then
      echo rm -rf $1
  　  rm -rf $1
  fi
  
  echo git clone $2
  git clone $2 $1

  cd $1
  echo mvn install -X -e
  mvn install -X -e
}

cloneAndInstall "../mybatis-generator-lombok-plugin/" "https://github.com/kimmking/mybatis-generator-lombok-plugin"
cloneAndInstall "../mybatis-generator-core" "https://git.oschina.net/kimmking/mybatis-generator-core.git"

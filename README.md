# nicefish-backend
nicefish-backend is the backend system for nicefish framework.

[![Build Status](https://travis-ci.org/nicefishcn/nicefish-backend.svg?branch=master)](https://travis-ci.org/nicefishcn/nicefish-backend)
[![Codecov](https://codecov.io/gh/nicefishcn/nicefish-backend/branch/master/graph/badge.svg)](https://codecov.io/gh/nicefishcn/nicefish-backend/branch/master)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

## architecture
1. j2ee
2. mysql
3. rest

## dependencies: framework and libraries

### j2ee
1. spring/spring-boot ![](https://img.shields.io/badge/springboot-done-green.svg?style=plastic)
2. mybatis/generator ![](https://img.shields.io/badge/mybatis-done-green.svg?style=plastic)
3. spring-mvc ![](https://img.shields.io/badge/springmvc-done-green.svg?style=plastic)
4. fastjson ![](https://img.shields.io/badge/fastjson-done-green.svg?style=plastic)
5. slf4j ![](https://img.shields.io/badge/slf4j-done-green.svg?style=plastic)
6. druid ![](https://img.shields.io/badge/druid-done-green.svg?style=plastic)
7. shiro ![](https://img.shields.io/badge/shiro-done-green.svg?style=plastic)
8. swagger ![](https://img.shields.io/badge/swagger-done-green.svg?style=plastic)
9. lombok ![](https://img.shields.io/badge/lombok-done-green.svg?style=plastic)


### tool
1. maven ![](https://img.shields.io/badge/maven-done-green.svg?style=plastic)
2. mybatis-generator ![](https://img.shields.io/badge/generator-done-green.svg?style=plastic)
3. SwaggerUI ![](https://img.shields.io/badge/swagger-done-green.svg?style=plastic)
4. tomcat/jetty ![](https://img.shields.io/badge/tomcat-done-green.svg?style=plastic)
5. mysql ![](https://img.shields.io/badge/mysql-done-green.svg?style=plastic)
6. script tools for Linux/Mac/Windows ![](https://img.shields.io/badge/Tools-done-green.svg?style=plastic)

## get started

### clone project
you can clone it in your console from git oschina:
```
git clone http://git.oschina.net/nicefish/nicefish-backend
```
or from github:
```
git clone https://github.com/nicefishcn/nicefish-backend
```
### build project
build it:

```
mvn package
```
or
```
mvn install
```

### start nicefish application
1. start your mysql server, and execute sql/nicefish.sql
2. then run project use:
```
sh run.sh # in Windows, just use: run.bat
```

### access demo
then you can access nicefish application in webbrowser or on console now:
1. you can login && list users:
In webbrowser, use this url:
- http://localhost:8080/auth/login?userName=kimmking&password=123456
- http://localhost:8080/user/list

2. you can access SwaggerUI and test rest api:
- http://localhost:8080/swagger-ui.html

On console:
```
$ curl -c cookie.txt "http://localhost:8080/auth/login?userName=kimmking&password=123456"
{"userId":"ca11816f-8480-469f-b33e-c705bb7d29ae","userName":"kimmking","password":"398a8c9d73da4c0e7f320952b06decdefcc97586305b0d51b1093ae95d8dc476ca63cc22ce64bbc344ad55b52d035cba5f3f2c21801afd561412f665bbcb5c45","email":"kimmking@163.com","realName":null,"nickName":null,"qq":null,"weixin":null,"cellPhone":null,"userDesc":null,"upId":null,"status":null,"regTime":1498461050000,"lastloginTime":null,"ename":null}bogon:nicefish-backend kimmking$

$ curl -b cookie.txt http://localhost:8080/user/list
[{"userId":"ca11816f-8480-469f-b33e-c705bb7d29ae","userName":"kimmking","password":"398a8c9d73da4c0e7f320952b06decdefcc97586305b0d51b1093ae95d8dc476ca63cc22ce64bbc344ad55b52d035cba5f3f2c21801afd561412f665bbcb5c45","email":"kimmking@163.com","realName":null,"nickName":null,"qq":null,"weixin":null,"cellPhone":null,"userDesc":null,"upId":null,"status":null,"regTime":1498461050000,"lastloginTime":null,"ename":null}]bogon:nicefish-backend kimmking$

```

## development
if you want to regenerate all mybatis models and mappers, execute in console:
```
sh env.sh # in Windows, just use: env.bat
sh gen.sh # in Windows, just use: gen.bat
```

- env.sh/env.bat to prepare required plugins in this project.
- gen.sh/gen.bat to generate mybatis codes from mysql.

### contribute
If you find any bug or some suggestion, click: [ISSUE-GIT-OSCHINA](http://git.oschina.net/nicefish/nicefish-backend/issues)

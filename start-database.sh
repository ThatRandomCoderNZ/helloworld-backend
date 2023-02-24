#!/bin/bash

docker run --name mysql-hwl -d \
    -p 3306:3306 \
    -e MYSQL_ROOT_PASSWORD=change-me \
    -v mysql-hwl:/var/lib/mysql \
    mysql:8
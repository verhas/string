#!/usr/bin/env bash

rm -rf release
echo Creating string
mkdir -p release/string
cp target/*jar release/string
cp pom.xml release/string

cd release
for artifact in *
do
    for file in ${artifact}/*.jar ${artifact}/pom.xml
    do
        echo Signing ${file}
        gpg -s -b ${file}
        mv ${file}.sig ${file}.asc
    done
    cd ${artifact}
    echo Creating ${artifact}_release.zip
    jar -c -M -f ${artifact}_release.zip *.jar pom.xml *.asc
    mv *.zip ..
    cd ..
done
cd ..
echo done.

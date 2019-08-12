# wgForgeBackend
Тестовое задание https://github.com/wgnet/wg_forge_backend .

В корне проекта выполнить:    
```
mvn clean package
```  
и затем после полного запуска контейнера с postgres из образа:  
 ```
 yzh44yzh/wg_forge_backend_env:1.1
 ```  
для выполнения 1 и 2 задания, перейти в каталог:  ```task_1_2/target``` и выполнить:  
```
java -jar task_1_2-1.0.jar
```  
После того как будет выполнено первых 2 задания, перейти из корневого катлога в ```task_server/target``` и выполнить:  
```
java -jar task_server-1.0.one-jar.jar
```  
для запуска сервера. Сервер построен на embedded-tomcat и для 6-го задания для изменения количества обрабатываемых запросов нужно изменить количество работающих поток в tomcat. Для изменения количеств работающих потоков запустить сервер с флагом  
```-t колличество потоков```
```
java -jar task_server-1.0.one-jar.jar -t 5
```
Настройки соединения с базой данных заданные в файлах ```config.properties``` в каждом проекте.  

Или можно скачать образ для первых двух заданий:  
```
docker pull mjv11/wgforgebackend_task12
```  
и запустив его:  
```
docker run -it mjv11/wgforgebackend_task12
 ```  
Для сервера можно скачать образ:  
```
docker pull mjv11/wgforgebackend_server
```  
и запустив его:  
```
docker run -p 8080:8080 mjv11/wgforgebackend_server
```

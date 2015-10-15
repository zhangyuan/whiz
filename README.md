# whiz
mqtt message service prototype

## How to run the service

### Setup mosquitto

The configurations of mosquitto are as follows:

```
listener 9001
protocol websockets

listener 1883
protocol mqtt
```

Start the mosquitto sever with the config file, for exmaple
```
mosquitto -c /usr/local/etc/mosquitto/mosquitto.conf
```

### Run the service

The service uses H2 database in memory, so there is no need to setup external database service. And the databse will be drap and then created every time ther service starts.

```
./gradlew jettyRun
```

### Try the service

1. Open <http://localhost:8080/whiz>.
2. Set username to `bar` and click the `update` button to set the current user as `bar`.
3. Hit <http://localhost:8080/whiz/api/messages/foo/bar/hello>, which indicated the user `foo` sends `bar` the message `hello`. Ideally, the message will appear on <http://localhost:8080/whiz> 

### APIs
* GET <http://localhost:8080/whiz/api/messages>
* GET <http://localhost:8080/whiz/api/messages/{sender}/{receiver}/{content}>
* GET <http://localhost:8080/whiz/api/users/{sender}/messages>



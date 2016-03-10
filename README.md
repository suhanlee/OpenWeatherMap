# OpenWeatherMap
OpenWeatherMap API for Java and Android

# How to use
## Download
Maven
```xml
<dependency>
  <groupId>com.devsh</groupId>
  <artifactId>openweathermap-library</artifactId>
  <version>0.1.0</version>
  <type>pom</type>
</dependency>
```
Gradle
```java
compile 'com.devsh:openweathermap-library:0.1.0'
```
## Code
```java
        OpenWeatherMap.initialize(API_KEY);
        OpenWeatherMap.getWeatherGeoLocation("37.566535", "126.977969", new OpenWeatherResponseCallback() {
            @Override
            public void onResponseSuccess(OpenWeatherResponse response) {
                OpenWeatherMap.printResponse(response);
            }

            @Override
            public void onResponseFail(int statusCode, String error) {

            }

            @Override
            public void onFailure(Call<OpenWeatherResponse> call, Throwable t) {

            }
        });
```

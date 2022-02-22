# Wersja 3. Po rozwiązaniu Zadania 1.

### Students Application
**Pobieranie listy studentów** - GET *http://localhost:8080/students*

**Pobieranie studenta po Id**  - GET *http://localhost:8080/students/{id}*

**Dodawanie studenta**         - POST *http://localhost:8080/students*
Przykładowe body:
```
{
   "firstName":"Dominik",
   "lastName":"Maciąg",
   "email":"xx@gmail.com"
}
```
**Modyfikacja całego zasobu student** - PUT *http://localhost:8080/students/{id}*
Przykładowe body:
```
{
   "firstName":"Michał",
   "lastName":"Maciąg",
   "email":"michal@gmail.com"
}
```

**Modyfikacja części zasobu student** - PATCH  *http://localhost:8080/students/{id}*
```
{
   "firstName":"Michałek"
}
```

### Publisher Application

w pliku application.properties ustawić swoje namiary na RabbitMQ.

**Wysyłanie na RabbitMQ notyfikacji z wykorzystaniem danych studenta.**
Logika:
Publisher Application za pomocą id studenta otrzymanego w request pobiera studenta z Students Appilication.
Wykorzystując dane pobranego studenta buduje notyfikacje i wysyła na RabbitMQ.

GET *http://localhost:8085/notifications?studentId={id}*


Funkcjonalność nie zawiera obsługi błędów.
Do prawidłowego przeprocesowanie potrzeba, aby Students Application był uruchomiony i istniał student o id podanym w parametrze studentId


### Reciver Application
Wpliku application.properties ustawić swoje namiary na RabbitMQ.
Nazwa kolejki na RabbitMQ musi się zgadzać z nazwą kolejki, która jest w kodzie.

Reciver automatycznie pobiera notyfikację z kolejki dzięki @RabbitListener.
Automatycznie pobrana notyfikacja zostaje wyświetlona w konsoli.

Jeśli chcesz pobrać notyfikację za pomocą REST API, możesz zakomentować @RabbitListener i wywołać endpoint:

GET *http://localhost:8090/notification*



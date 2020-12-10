This is a [**Ktor**](https://ktor.io/) backend for my [Android-Sample app](https://github.com/Benjiko99/Android-Sample).

Architecture
------------
**Modules**
- Define routes/endpoints
- Respond to API requests
- Check validity of parameters
- Convert DTOs to Domain objects
- Handle exceptions

**Interactors**
- Domain-level business logic
- Uses data sources to retrieve or store data

**Data Sources**
- Communicate with external network resources
- Implement a local database
- Cache data in memory

Libraries
---------
Dependency Injection is done through **Koin**.

License
-------

    This is free and unencumbered software released into the public domain.

    For more information, please refer to either <http://unlicense.org> or the **LICENSE** file.

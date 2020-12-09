package data.memory.exceptions

class EntityNotFoundException(entityId: Any) :
    RuntimeException("Entity with ID: \"$entityId\" not found.")

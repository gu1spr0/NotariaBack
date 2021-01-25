package com.yorisapp.notaria.exception;

public enum MessageDescription {
    //General 0-50
    stateNullOrEmpty,
    stateNotValid,
    validacionCampoVacioNulo,
    tokenNullOrEmpty,
    objectNull,

    // Entitys 101-150
    repeated,
    notExists,
    EntityDuplicated,

    // contraseñas  251-300
    ContraseniaIncorrecta,

    // Usuarios 301-350
    UsernameNoEncontrado,
    UsernameDuplicado,
    UserWithoutRoles,
    UserWithoutResources,
    UserWithoutPermissions,
    UserAccessDenied,
}

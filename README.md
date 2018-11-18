# MercadoPago

## Autenticarse 

La aplicación debe obtener un token para poder operar a nombre de otro usuario. El token se obtiene con esta URL:

https://api.mercadopago.com/oauth/token?client_id=clientid&client_secret=clientsecret&grant_type=client_credentials

Reemplazar clienteid y clientsecret con los corespondientes a la aplicación.

## Crear usuarios de prueba

Para crear usuarios de prueba, primero obtener el token, y luego hacer un POST a:

https://api.mercadopago.com/users/test_user?access_token=token

Reemplazando token por el token obtenido.

## Solicitud de autorización

Estos pasos está descritos acá: https://www.mercadopago.com.ar/developers/es/solutions/payments/basic-checkout/mp-applications/

Para ejecutar funciones a nombre de una aplicación, el usuario debe autorizar a aplicación. La aplicación debe
dirigiar al usuario a esta URL:

https://auth.mercadopago.com.ar/authorization?client_id=[clientid]&response_type=code&platform_id=mp&redirect_uri=http%3A%2F%2Fwww.URL_de_retorno.com

Una vez que el usuario da su autorización, MercadoPago realiza un GET a la URL de retorno, con un parámetro code. Ese
código debe usarse para obtener las credenciales del usuario, haciendo un POST a la URL https://api.mercadopago.com/oauth/token
con los siguientes parámetros:

* client_id: El APP_ID.
* client_secret: El CLIENT_SECRET.
* code: El código de autorizacion
* redirect_uri: Debe ser la misma Redirect URI que configuraste en tu aplicación.

## Consola

Esta es la consola de las credenciales. Imposible llegar si no es con google:

https://www.mercadopago.com/mla/account/credentials

# Proceso de homologación

[Acá](https://www.mercadopago.com.ar/developers/es/solutions/payments/custom-checkout/appliance-requirements/) 
está documentado el proceso de homologación de aplicaciones.
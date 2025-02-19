# Proyecto Yoo! 💻 Backend

## Descripción
Este proyecto es una aplicación web diseñada para **permitir a los usuarios gestionar sus perfiles de red social de manera organizada y eficiente**. La plataforma facilita el seguimiento de elementos clave, permitiendo a los usuarios **añadir, modificar y eliminar registros** en una interfaz intuitiva.  
El backend de esta aplicación está construido con **Java y Springboot**, proporcionando una API REST robusta para la gestión de datos y autenticación de usuarios. La base de datos utilizada es **MySQL**, garantizando almacenamiento estructurado y consultas eficientes.  
El sistema incorpora **autenticación con JWT**, asegurando que solo usuarios autorizados puedan acceder a determinadas funcionalidades. Además, implementa medidas de seguridad como **hash de contraseñas con bcrypt**, validaciones en los endpoints y middleware para la protección de datos.  


¡Adelante!

## Tecnologías utilizadas
### Backend:
- Java con Springboot
- Base de datos: MySQL
- Autenticación con JWT o OAuth


### Frontend:
- React con Vite
- Tailwind CSS o Bootstrap

## Instalación y Configuración
### Backend:
1. Clonar el repositorio:
   ```sh
   git clone https://github.com/tu-usuario/backend-repo.git
   ```
2. Instalar dependencias:
   ```sh
   cd backend-repo
   npm install
   ```
3. Configurar variables de entorno (crear un archivo `.env` con las credenciales necesarias).
4. Iniciar el servidor:
   ```sh
   npm start
   ```

### Frontend:
1. Clonar el repositorio:
   ```sh
   git clone https://github.com/tu-usuario/frontend-repo.git
   ```
2. Instalar dependencias:
   ```sh
   cd frontend-repo
   npm install
   ```
3. Iniciar la aplicación:
   ```sh
   npm run dev
   ```

## Autenticación y Seguridad
- Autenticación basada en JWT/OAuth.
- Hash de contraseñas con bcrypt.
- Middleware para validación de sesiones y roles.
- Protección contra ataques XSS y CSRF.

## API Endpoints
| Método | Endpoint | Descripción |
|--------|---------|-------------|
| GET | `/api/v1/usuarios` | Obtiene todos los usuarios |
| POST | `/api/v1/auth/login` | Inicia sesión |
| POST | `/api/v1/auth/register` | Registra un usuario |
| GET | `/api/v1/publicaciones` | Obtiene todas las publicaciones |

## Modelo ER
<img src="Diagrama ER de red social Yoo!.png"/>

## Enlaces
- [Repositorio Frontend]((https://github.com/LauraAmaya08/yoo-app)

## Últimos Hashes de Commits
### Frontend:
```
Último commit: 3b1f1dad97ff7c0ac60e6197e7854af720ef8c3a

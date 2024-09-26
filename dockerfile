# Usar la imagen base oficial de MySQL
FROM mysql:8.0

# Establecer variables de entorno para el root
ENV MYSQL_ROOT_PASSWORD=pass123
ENV MYSQL_DATABASE=mysqldaniel
ENV MYSQL_USER=Daniel
ENV MYSQL_PASSWORD=D123

# Copiar un archivo SQL si quieres inicializar la base de datos
# COPY ./inicializacion.sql /docker-entrypoint-initdb.d/

# Exponer el puerto de MySQL
EXPOSE 3306

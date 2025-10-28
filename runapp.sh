#!/bin/bash

#   ================================
# Construir jar de Gradle
# ================================
echo "Generando el jar..."
./gradlew clean bootJar
if [ $? -ne 0 ]; then
    echo "Error al generar el jar"
    exit 1
fi

# ================================
# Bajar contenedores y volúmenes antiguos
# ================================
echo "Deteniendo contenedores antiguos y eliminando volúmenes..."
docker compose down -v

# ================================
# Construir la imagen Docker
# ================================
echo "Construyendo imagen Docker..."
docker compose build
if [ $? -ne 0 ]; then
    echo "Error al construir la imagen Docker"
    exit 1
fi

# ================================
# Levantar los contenedores
# ================================
echo "Levantando contenedores..."
docker compose up -d
if [ $? -ne 0 ]; then
    echo "Error al levantar los contenedores"
    exit 1
fi

echo "✅ Todo listo"

@echo off
setlocal

set JAVAFX_PATH=lib
set MAIN_CLASS=appInterface.MainApp
set JAR_NAME=LinguaConnect.jar
set SRC_DIR=src
set OUT_DIR=out

echo Nettoyage...
rmdir /s /q %OUT_DIR%
del /q %JAR_NAME%
del /q sources.txt
mkdir %OUT_DIR%

echo Recherche des fichiers source...
dir /S /B %SRC_DIR%\*.java > sources.txt

echo Compilation Java...
javac --module-path %JAVAFX_PATH% --add-modules javafx.controls,javafx.fxml -d %OUT_DIR% @sources.txt

if errorlevel 1 (
    echo Erreur de compilation
    exit /b 1
)

mkdir %OUT_DIR%\appInterface
copy %SRC_DIR%\appInterface\linguaConnect.fxml %OUT_DIR%\appInterface\ >nul

echo Création du JAR...
jar --create --file %JAR_NAME% --main-class %MAIN_CLASS% -C %OUT_DIR% .

echo Contenu du JAR :
jar tf %JAR_NAME%

echo Lancement de l'application...
java --module-path %JAVAFX_PATH% --add-modules javafx.controls,javafx.fxml -jar %JAR_NAME%

endlocal
pause

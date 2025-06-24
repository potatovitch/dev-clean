#!/bin/bash

JAVAFX_PATH="/home/iutinfo/S2/javafx-sdk-21.0.7/lib"
MAIN_CLASS="appInterface.MainApp"
JAR_NAME="LinguaConnect.jar"
SRC_DIR="src"
OUT_DIR="out"

echo
rm -rf "$OUT_DIR" "$JAR_NAME"
mkdir -p "$OUT_DIR/appInterface"

echo
javac \
  --module-path "$JAVAFX_PATH" \
  --add-modules javafx.controls,javafx.fxml \
  -d "$OUT_DIR" \
  $(find "$SRC_DIR" -name "*.java")

if [ $? -ne 0 ]; then
  echo "Erreur de compilation"
  exit 1
fi

echo
mkdir -p "$OUT_DIR/appInterface"
cp "$SRC_DIR/appInterface/linguaConnect.fxml" "$OUT_DIR/appInterface/"

echo
jar --create \
  --file "$JAR_NAME" \
  --main-class "$MAIN_CLASS" \
  -C "$OUT_DIR" .

jar tf "$JAR_NAME" | grep "linguaConnect.fxml"

echo 
java \
  --module-path "$JAVAFX_PATH" \
  --add-modules javafx.controls,javafx.fxml \
  -jar "$JAR_NAME"

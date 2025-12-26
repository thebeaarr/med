# Makefile for Java project

# Compiler
JC = javac

# Source files
SRC = $(wildcard *.java)

# Output folder for classes
BIN = bin

# Create bin folder if it doesn't exist
$(BIN):
	mkdir -p $(BIN)

# Compile all .java files
classes: $(BIN)
	$(JC) -d $(BIN) $(SRC)

# Run Main class
run: classes
	java -cp $(BIN) Main

# Clean compiled classes and docs
clean:
	rm -rf $(BIN) docs

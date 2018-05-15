TARGET = SimpleCompiler
VERSION = v1.0
# Common commands
JC = javac
JC_FLAG = -Xlint:all -Xlint:unchecked -Xdiags:verbose

JD = javadoc
JD_FLAG =

JR = java
JR_FLAG = 

JP = jar
JP_FLAG =

RM = rm
RM_FLAG = -rf

# Basic directories
BIN = bin
SRC = src
LIB = lib
DOC = doc

# Colors
RED= \033[1;31m
YELLOW = \033[1;33m
GREEN = \033[1;32m
BLUE = \033[1;34m
NC = \033[0m

ERROR = $(RED)
WARNING = $(YELLOW)
NICE = $(GREEN)
NOTE = $(BLUE)

# Include files
README = ../README.md
LICENSE = META-INF/LICENSE
INCLUDE = $(JR_MANIFEST) $(README) $(LICENSE) $(DOC) $(SDK_VERSION) $(TRUSTED)

# Libs
CP = $(BIN)

# Packages
OLD = legacy
NEW = modification

################################################################################
Main-Class = $(NEW).Compiler

all: $(NEW)
	@echo "$(NOTE)Done$(NC)"

#######################################################################################################################################################
## new code, modifications

CLASS_NEW = Compiler \
		LexicalAnalyzer \
		Token \
		Interpreter \
		Div \
		Exp \
		Mult \
		Num \
		Operator \
		Parser \
		ParseTree \
		Sub \
		Sum \
		Token \
		TokenType 

# HERE
$(NEW): $(addsuffix .java, $(addprefix $(SRC)/$(NEW)/, $(CLASS_NEW)))
	$(JC) $(JC_FLAG) -cp $(CP) $(addsuffix .java, $(addprefix $(SRC)/$(NEW)/, $(CLASS_NEW))) -d $(BIN)
	@echo "$(NOTE)Compiled all legacy code$(NC)"

#######################################################################################################################################################
## legacy code

CLASS_OLD =  AnaliseLexica \
	     ArvoreSintatica \
	     CodeGen \
	     Compilador \
	     Parser

$(OLD): $(addsuffix .java, $(addprefix $(SRC)/$(OLD)/, $(CLASS_OLD)))
	$(JC) $(JC_FLAG) -cp $(CP) $(addsuffix .java, $(addprefix $(SRC)/$(OLD)/, $(CLASS_OLD)))
	@echo "$(NOTE)Compiled all legacy code$(NC)"

#######################################################################################################################################################
# run some code
#test:
#	$(JR) $(JR_FLAG) -cp $(BIN) $(BIN)/$(NEW).$(Main-Class) $1

#######################################################################################################################################################

documentation: $(ALL_CLASSES)
	$(JD) $(JD_FLAG) -cp $(LIBRARIES):.:$(BIN) $(ALL_CLASSES) -d $(DOC)/

#jarfile: clean all documentation
#	$(JP) $(JP_FLAG)cfm $(TARGET)_$(VERSION).jar $(JR_MANIFEST) -C $(BIN)/ . $(INCLUDE)

clean:
	$(RM) $(RM_FLAG) $(DOC)/* || true
	$(RM) -r $(RM_FLAG) $(BIN)/*
	$(RM) -f $(SRC)/***/*.class || true
	$(RM) $(RM_FLAG) $(TARGET)_$(VERSION)*.jar || true

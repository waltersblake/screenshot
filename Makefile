PACKAGE=screenshot
BUILDDIR=~/dev/java/ScreenShot/build
SOURCEDIR=~/dev/java/ScreenShot/src/main/$(PACKAGE)

target:
	javac -d $(BUILDDIR) -Xdiags:verbose $(SOURCEDIR)/*.java 

run:target
	java -cp $(BUILDDIR) $(PACKAGE).ScreenShot

clean:
	rm $(BUILDDIR)/$(PACKAGE)/*.class
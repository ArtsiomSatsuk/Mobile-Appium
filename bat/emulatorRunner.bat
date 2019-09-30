cd %ANDROID_HOME%\emulator
emulator -avd %1 -port %2 -no-boot-anim -no-snapshot-save -no-audio
exit
for /f "tokens=5" %%P in (
     'netstat -ano ^| find ":%1" ^| find /i "LISTENING"'
) do taskkill /f /pid %%P
exit
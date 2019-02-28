call runcrud
if "%ERRORLEVEL%" == "0" goto openbrowser
echo.
echo RUNCRUD has errors - breaking work

:openbrowser
start chrome.exe http://localhost:8080/crud/v1/task/getTasks

:end
echo.
echo Work is Finished

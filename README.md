SOAP Service given persons that were born on particular year.

Install project:
1. install maven project
2. run karaf.bat
3. feature:repo-add cxf 3.1.5
4. feature:install http http-whiteboard cxf-jaxws
5. kar:install mvn:com.fronya/features-karaftask/1.0-SNAPSHOT/kar

http://localhost:8181/cxf/personServiceSoap - SOAP service

http://localhost:8181/cxf/personServiceSoap?wsdl - wsdl

http://localhost:8181/personclient - client for test service

+ add tests for class PersonServiceImpl 
from zeep import Client
client = Client(wsdl='http://35.174.204.25/axis2/services/DobService?wsdl')
age = input("age >>>")
print(client.service.getYear(age));

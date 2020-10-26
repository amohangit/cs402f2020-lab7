from zeep import Client
client = Client(wsdl='http://54.160.98.24/axis2/services/DobService?wsdl')
age = input("age >>>")
print(client.service.getYear(age));

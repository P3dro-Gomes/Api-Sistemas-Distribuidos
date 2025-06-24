import requests

class RequestsClass:

    dominio = "http://localhost:8080"

    def get_orcamento(self):
        url = self.dominio + "/orcamento"
        response = requests.get(url)
        
        return response.json()

    def post_orcamento(self, solicitante:str):
        area = input("Digite a area: ")
        saldoDisponivel = input("Digite o saldo: ")
        
        url = self.dominio + "/orcamento"
        body = {
            "area" : area,
            "saldoDisponivel" : saldoDisponivel,
            "solicitante" : solicitante
            }
        response = requests.post( url, json= body)

        return response.json()

    def delete_orcamento(self):
        area = input("Digite a area: ")
        url = self.dominio + f"/orcamento/{area}"
        response = requests.delete(url)

        return response.json()

    def get_orcamento_area(self):
        area = input("Digite a area: ")
        url = self.dominio + f"/orcamento/{area}"
        response = requests.get(url)
        
        return response.json()

    def put_orcamento_adicionar(self):
        area = input("Digite a area: ")
        valor = input("Digite o valor: ")
        
        url = self.dominio + f"/orcamento/{area}/adicionar"
        response = requests.put(url, body= valor )
        
        return response.json()

    def put_orcamento_atualizar_saldo(self):
        area = input("Digite a area: ")
        valor = input("Digite o valor: ")
        
        url = self.dominio + f"/orcamento/{area}/saldo"
        response = requests.put(url, body= valor )  
        
        return response.json() 

    def post_despesas(self):
        area = input("Digite a area: ")
        valor = input("Digite o valor: ")
        
        url = self.dominio + "/despesas"
        body = {
            "area" : area,
            "valor" : valor 
        }
        response = requests.post(url, json= body )
        print(response.json())
        return response.json()
        
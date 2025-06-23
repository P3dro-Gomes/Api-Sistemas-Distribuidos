import requests
dominio = "http://localhost:8080"


def get_orcamento():
    url = dominio + "/orcamento"
    response = requests.get(url)

def post_orcamento(area:str, saldoDisponivel:int, solicitante:str):
    url = dominio + "/orcamento"
    body = {
        "area" : area,
        "saldoDisponivel" : saldoDisponivel,
        "solicitante" : solicitante
    }
    response = requests.post(url, json= body)
    
def delete_orcamento(area:str):
    url = dominio + f"/orcamento/{area}"
    response = requests.delete(url)


def get_orcamento_area(area:str):
    url = dominio + f"/orcamento/{area}"
    response = requests.get(url)
    
def put_orcamento_adicionar(area:str, valor:str):
    url = dominio + f"/orcamento/{area}/adicionar"
    response = requests.put(url, body= valor )

def put_orcamento_atualizar_saldo(area:str, valor:str):
    url = dominio + f"/orcamento/{area}/saldo"
    response = requests.put(url, body= valor )   

def post_despesas(area:str, valor):
    url = dominio + "/despesas"
    body = {
        "area" : area,
        "valor" : valor 
    }
    response = requests.post(url, json= body )
    print(response.json())

nome_usuario = input("Digite seu nome: ")
while True:
    
    dicionario = {
        "1" : get_orcamento(),
        "2" : post_orcamento(),
        "3" : delete_orcamento(),
        "4" : get_orcamento_area(),
        "5" : put_orcamento_adicionar(),
        "6" : put_orcamento_atualizar_saldo(),
        "7" : post_despesas()
    }
    
    
    
    print("""---- gerenciamento ----
1 -> GET /orçamento
2 -> POST /orçamento
3 -> DELETE /orçamento
4 -> GET /orçamento/{\area}
5 -> PUT /orçamento/{area}/adicionar
6 -> PUT /orçamento/{area}/saldo
7 -> POST /despesas
          """)
    
    input_usuario = input("Digite um numero: ")
    
        
    
    
    
from Requests_class import RequestsClass
import subprocess
import time 

cmd1 = subprocess.Popen('start cmd /k "cd C:\\API - Romulo\\Api-Sistemas-Distribuidos\\api - 1\\api && mvn quarkus:dev"', shell=True)

cmd2 = subprocess.Popen('start cmd /k "cd C:\\API - Romulo\\Api-Sistemas-Distribuidos\\api - 2\\api && mvn quarkus:dev"', shell=True)

time.sleep(60)

r = RequestsClass()

nome_usuario = input("Digite seu nome: ")

while True:
    print("""
---- Gerenciamento ----
1 -> GET /orcamento
2 -> POST /orcamento
3 -> DELETE /orcamento
4 -> GET /orcamento/{area}
5 -> PUT /orcamento/{area}/adicionar
6 -> PUT /orcamento/{area}/saldo
7 -> POST /despesas
0 -> Sair
""")

    input_usuario = input("Digite um número: ")

    dicionario = {
        "1": r.get_orcamento,
        "2": lambda: r.post_orcamento(nome_usuario),
        "3": r.delete_orcamento,
        "4": r.get_orcamento_area,
        "5": r.put_orcamento_adicionar,
        "6": r.put_orcamento_atualizar_saldo,
        "7": r.post_despesas
    }

    if input_usuario == "0":
        print("Saindo...")
        break

    if input_usuario in dicionario:
        resultado = dicionario[input_usuario]()
        print(f"Resultado: {resultado}")
    else:
        print("Opção inválida. Tente novamente.")    
    
    time.sleep(4)
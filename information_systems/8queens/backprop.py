
# O tamanho do tabuleiro, razão do problema
N = 16

# O tabuleiro a ser usado
tabuleiro = [ [ 0 for x in range(N)] for y in range(N)]

# Mapeia a posição de todas as rainhas inseridas, tem o intuito de aumentar performance do algoritmo
queenMap = {}


def posição_valida(linha, coluna):
    """
        Verifica se para uma dada posição (linhaXColuna)
        é possível inserir uma rainha sem que as outras
        ameacem ela.

    :param linha: Linha do ponto candidato
    :param coluna: Coluna do ponto candidato
    :return: True se é uma posição válida, False caso contrário.
    """
    if queenMap:
        if linha in queenMap:
            return False
        for lin in queenMap:
            if queenMap[lin] == coluna:
                return False
            delta_linha = abs(lin - linha)
            delta_coluna = abs(queenMap[lin] - coluna)
            if delta_linha == delta_coluna:
                return False

    return True



def inserirRainha(linha):
    """
        Soluciona um problema NxN das rainhas usando backtracking
    :param linha: A linha de início a ser avaliada
    :return: None
    """
    for coluna in range( len(tabuleiro[linha]) ):
        if posição_valida(linha, coluna):
            tabuleiro[linha][coluna] = 1
            queenMap[linha] = coluna
            if ( linha == N-1):
                print("Solução encontrada!!!")
                return True
            else:
                solution = inserirRainha(linha + 1)
                if solution:
                    return True
            tabuleiro[linha][coluna] = 0
            queenMap.pop(linha)


# Execução do algoritmo

inserirRainha(0)
for linha in tabuleiro:
    print(linha)
print("\nPosição das rainhas: ")
for linha in queenMap:
    print("linha : %d  coluna %d" % (linha+1 , queenMap[linha]+1) )
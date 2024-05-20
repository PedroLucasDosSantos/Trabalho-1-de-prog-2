package com.mycompany.trabalho1;

import java.util.*;

public class SpamDetector {
    private static final List<Term> spamTerms = Arrays.asList(
        new Term("você ganhou", 8),
        new Term("tentativa de entrega frustrada", 7),
        new Term("empréstimos para negativado", 10),
        new Term("clique aqui", 9),
        new Term("parabéns", 8),
        new Term("oferta imperdível", 7),
        new Term("limite de crédito", 10),
        new Term("dinheiro fácil", 9),
        new Term("desconto especial", 6),
        new Term("confira agora", 5)
    );

    private static class Term {
        String phrase;
        int weight;

        Term(String phrase, int weight) {
            this.phrase = phrase;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
      
        if (args.length != 1) {
            System.out.println("Uso: java SpamDetector <caminho do arquivo>");
            return;
        }

        String caminho = args[0];
        LeitorArquivo leitorArquivo = new LeitorArquivo();
        String texto = leitorArquivo.lerArquivo(caminho);
        if (texto.isEmpty()) {
            System.out.println("Arquivo vazio ou não encontrado.");
            return;
        }

        int pontuacao = calcularPontuacao(texto);
        classificarTexto(pontuacao);
    }

    private static int calcularPontuacao(String texto) {
        int pontuacao = 0;
        texto = texto.toLowerCase(); 
        for (Term term : spamTerms) {
            if (texto.contains(term.phrase)) {
                pontuacao += term.weight;
            }
        }
        return pontuacao;
    }

    private static void classificarTexto(int pontuacao) {
        System.out.println("Pontuação: " + pontuacao);
        if (pontuacao > 70) {
            System.out.println("Classificação: Spam");
        } else if (pontuacao > 30) {
            System.out.println("Classificação: Potencialmente suspeito");
        } else {
            System.out.println("Classificação: Fracamente suspeito");
        }
    }
}

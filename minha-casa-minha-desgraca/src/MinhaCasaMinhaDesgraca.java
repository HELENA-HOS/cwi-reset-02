import domain.Beneficiario;
import domain.Endereco;
import domain.UnidadeFederativa;
import heranca.Apartamento;
import heranca.Casa;
import heranca.Imovel;
import service.ImoveisParaFinanciamento;
import service.PropostaFinanciamento;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MinhaCasaMinhaDesgraca {

    public static void main(String[] args) throws Exception {

        System.out.println("\n ### Olá, seja bem vindo ao magnífico programa Minha heranca.Casa - Minha Desgraça ###\n");

        ImoveisParaFinanciamento opcoesParaFinanciamento = new ImoveisParaFinanciamento();



        /**
         * INÍCIO
         *
         * NÃO MODIFICAR ESTA CLASSE ANTES DESTA LINHA.
         */

        Endereco enderecosp = new Endereco("Rua Silveira", 200, "bloco A", "Vila Maria", "São Paulo", UnidadeFederativa.SP);
        Endereco enderecorj = new Endereco("Rua Lapa", 67, "ap. 01", "Vila Maracanã", "Rio de Janeiro",UnidadeFederativa.RJ);
        Endereco enderecosc = new Endereco("Rua Navegantes", 80, "casa 3", "Vila Ocean", "Penha",UnidadeFederativa.SC);
        Endereco enderecopr = new Endereco("Rua Araucária", 165, "s/ compl.", "Água Verde", "Curitiba",UnidadeFederativa.PR);
        Endereco enderecomg = new Endereco("Rua Minas", 97, "ap. 42", "Pampulha", "Belo Horizonte",UnidadeFederativa.MG);



        Casa casa01 = new Casa(enderecosp, 100000d,"heranca.Casa",true);
        Apartamento ap01 = new Apartamento(enderecorj,200000d,"heranca.Apartamento","1º");
        Casa casa02 = new Casa(enderecosc, 300000d,"heranca.Casa",false);
        Casa casa03 = new Casa(enderecopr,49000d,"heranca.Casa",true);
        Apartamento ap02 = new Apartamento(enderecomg,80000d,"heranca.Apartamento","4º");

        //domain.Beneficiario jose = new domain.Beneficiario("José Souza", 5000.00);

        opcoesParaFinanciamento.registrarImovel(casa01);
        opcoesParaFinanciamento.registrarImovel(ap01);
        opcoesParaFinanciamento.registrarImovel(casa02);
        opcoesParaFinanciamento.registrarImovel(casa03);
        opcoesParaFinanciamento.registrarImovel(ap02);


        /**
         * FIM
         *
         * NÃO MODIFICAR ESTA CLASSE APÓS ESTA LINHA.
         */



        List<Imovel> todasAsOpcoes = opcoesParaFinanciamento.buscarOpcoes(Double.MAX_VALUE);
        if (todasAsOpcoes == null || todasAsOpcoes.isEmpty()) {
            throw new RuntimeException("\n\nAtenção! Você precisa registrar opções de financiamento antes de começar a usar o programa.\n"
                + "Use o método 'registrarImovel', do objeto 'opcoesParaFinanciamento', para incluir algumas opções.\n");
        }

        System.out.println("\nVamos lá, preciso de algumas respostas...\n");
        Thread.sleep(1000);

        System.out.println(" Qual é o teu nome?");
        String nomeBeneficiario = new Scanner(System.in).next();
        Thread.sleep(500);

        System.out.println(" Agora eu preciso saber sobre a mascada, "+ nomeBeneficiario +". Qual é o teu salário?");
        double salarioBeneficiario = new Scanner(System.in).nextDouble();
        Thread.sleep(500);

        Beneficiario beneficiario = new Beneficiario(nomeBeneficiario, salarioBeneficiario);

        System.out.println(" Hummmm, tá grandão hein. E qual é o limite do valor de imóveis que você procura?");
        double valorParaPesquisa = new Scanner(System.in).nextDouble();
        Thread.sleep(500);

        System.out.println("\nTá, deixa eu ver aqui o que eu tenho de opções até "+ DecimalFormat.getCurrencyInstance().format(valorParaPesquisa) +"...");
        Thread.sleep(new Random().nextInt(4000) + 1000);

        List<Imovel> opcoesViaveis = opcoesParaFinanciamento.buscarOpcoes(valorParaPesquisa);
        if (opcoesViaveis == null || opcoesViaveis.isEmpty()) {
            throw new RuntimeException("\n\nPQP! Essa desgraça de programa não tem casas neste valor! Comece uma nova simulação.");
        }

        System.out.println("\nCerto! Encontrei "+ opcoesViaveis.size() + " opções aqui. Dá uma olhada:");
        int index = 0;
        for (Imovel imovel : opcoesViaveis) {
            System.out.printf(" [%d] %s\n", ++index, imovel.apresentacao());
            Thread.sleep(250);
        }

        Imovel imovelEscolhido = null;
        while (imovelEscolhido == null) {

            Thread.sleep(250);

            System.out.println("\nQual a opção que mais te agrada? (Me diz apenas o número.)");
            int opcaoEscolhida = new Scanner(System.in).nextInt();

            Thread.sleep(250);

            if (opcaoEscolhida < 1 || opcaoEscolhida > opcoesViaveis.size()) {
                System.out.println(" > Opção inválida!");
            } else {
                imovelEscolhido = opcoesViaveis.get(opcaoEscolhida - 1);
            }
        }

        System.out.println("\nShow de bola! Agora me diz em quantos meses você quer pagar essa tranqueira:");
        int mesesParaPagamento = new Scanner(System.in).nextInt();

        System.out.printf("\nHumm, certo %s. Você ganha %s e quer financiar um imóvel de %s, no estado %s, pagando em %d meses. Deixa eu ver se é possível...\n\n",
                beneficiario.getNome(),
                DecimalFormat.getCurrencyInstance().format(beneficiario.getSalario()),
                DecimalFormat.getCurrencyInstance().format(imovelEscolhido.getValor()),
                imovelEscolhido.getEndereco().getEstado(),
                mesesParaPagamento);
        Thread.sleep(new Random().nextInt(4000) + 1000);

        PropostaFinanciamento financiamento = new PropostaFinanciamento(beneficiario, imovelEscolhido, mesesParaPagamento);
        financiamento.validarProposta();
    }
}

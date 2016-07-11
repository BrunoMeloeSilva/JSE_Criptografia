package bcms.criptografia;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

public class MD5 {
	//O código no main é só para esclarecimentos e visualização dos detalhes para melhor entendimento.
	//Caso deseje usar em seu código, copie o método criado após o método main, que já está pronto para uso.
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// Valor que será criptografado (hash em MD5)
		String senha = "admin";
		// Obtém uma instancia do algoritmo de criptografia (do tipo MD5)
		MessageDigest algoritmo = MessageDigest.getInstance("MD5");
		// Obtém o valor Hash unidirecional (o valor criptografado em MD5,
		// conforme setado acima)
		byte messageDigest[] = algoritmo.digest(senha.getBytes("UTF-8"));

		// Saídas:
		System.out.println("Algotirmo: " + algoritmo);
		System.out.println("Entrada em bytes: " + Arrays.toString(senha.getBytes("UTF-8")) + "\n");
		System.out.println("Vetor de saida em bytes: " + Arrays.toString(messageDigest));

		// Transformando o vetor de bytes (messageDigest) em um vetor de
		// hexadecimais (hexadecimal)
		ArrayList<String> hexadecimal = new ArrayList<>();
		for (byte b : messageDigest) {
			hexadecimal.add(String.format("%02x", 0xFF & b));
		}
		System.out.println("Vetor de saida transformado em Hexadecimal: " + hexadecimal);

		// A tranformacao acima representada em uma string, pois comumente é
		// usado assim
		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
			hexString.append(String.format("%02x", 0xFF & b));
		}
		System.out.println("String hexadecimal: " + hexString.toString());
	}

	/**
	 * @author brunomeloesilva
	 * 
	 * @param tipoAlgoritmo
	 *            Informe o tipo do algoritmo, podendo ser um MD5, "MD4",
	 *            "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512",
	 *            "RIPEMD128", "RIPEMD160", "RIPEMD256", "RIPEMD320", "Tiger",
	 *            "DHA256" e "FORK256".
	 * @param entrada
	 *            String que deverá ser criptografada conforme o tipoAlgoritmo
	 *            informado
	 * @return Uma representacao hexadecimal em string do retorno da execução do
	 *         algotimo message digest, conforme tipoAlgoritmo informado
	 *         
	 * @see Fontes: 
	 * 	 https://pt.wikipedia.org/wiki/MD5
	 * , http://www.devmedia.com.br/como-funciona-a-criptografia-hash-em-java/31139
	 */
	public static String getStringHexadecimalHash(String tipoAlgoritmo, String entrada) {
		MessageDigest algoritmo = null;
		byte messageDigest[] = null;
		try {
			// Obtém uma instancia do algoritmo de criptografia (do tipo MD5,
			// SSH1, etc..)
			algoritmo = MessageDigest.getInstance(tipoAlgoritmo);
			// Obtém o valor Hash unidirecional (ex.: o valor criptografado
			// conforme tipo setado acima)
			messageDigest = algoritmo.digest(entrada.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// Transformando o vetor de bytes (messageDigest) em um vetor de
		// hexadecimais (hexadecimal) representado numa string
		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
			hexString.append(String.format("%02x", 0xFF & b));
		}
		return hexString.toString();
	}
}

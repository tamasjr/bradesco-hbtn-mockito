package mockito;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BancoServiceTest {

    @Test
    public void testConsultarSaldo() {
        ContaRepository contaRepository = mock(ContaRepository.class);
        Conta conta = new Conta("123", 100.0);
        when(contaRepository.buscarConta("123")).thenReturn(conta);

        BancoService bancoService = new BancoService(contaRepository);

        double saldo = bancoService.consultarSaldo("123");
        assertEquals(100.0, saldo);
        verify(contaRepository).buscarConta("123");
    }

    @Test
    public void testDepositar() {
        ContaRepository contaRepository = mock(ContaRepository.class);
        Conta conta = new Conta("123", 100.0);
        when(contaRepository.buscarConta("123")).thenReturn(conta);

        BancoService bancoService = new BancoService(contaRepository);

        bancoService.depositar("123", 50.0);

        assertEquals(150.0, conta.getSaldo());
        verify(contaRepository).buscarConta("123");
        verify(contaRepository).salvar(conta);
    }
}

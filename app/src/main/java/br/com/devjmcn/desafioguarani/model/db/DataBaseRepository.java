package br.com.devjmcn.desafioguarani.model.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.devjmcn.desafioguarani.model.Repository;
import br.com.devjmcn.desafioguarani.model.models.Clients;
import io.reactivex.rxjava3.core.Observable;

public class DataBaseRepository implements Repository {

    private final DataBaseHelper dataBaseHelper;

    @Inject
    public DataBaseRepository(DataBaseHelper dataBaseHelper) {
        this.dataBaseHelper = dataBaseHelper;
    }

    @Override
    public Observable<List<Clients>> getAllClients(String status) {
        return Observable.create(emitter -> {
            List<Clients> clientList = new ArrayList<>();

            String query = "SELECT * FROM GUA_CLIENTES WHERE CLI_RAZAOSOCIAL LIKE ?";

            try (SQLiteDatabase db = dataBaseHelper.openDatabase()) {

                Cursor cursor = db.rawQuery(query, new String[]{"%"+status+"%"});

                if (cursor != null && cursor.getCount() > 0) {
                    int columnCod = cursor.getColumnIndexOrThrow("CLI_CODIGOCLIENTE");
                    int columnRazaoSocial = cursor.getColumnIndexOrThrow("CLI_RAZAOSOCIAL");
                    int columnNomeFantasia = cursor.getColumnIndexOrThrow("CLI_NOMEFANTASIA");
                    int columnCpf = cursor.getColumnIndexOrThrow("CLI_CGCCPF");
                    int columnEndereco = cursor.getColumnIndexOrThrow("CLI_ENDERECO");
                    int columnNumero = cursor.getColumnIndexOrThrow("CLI_NUMERO");
                    int columnComplemento = cursor.getColumnIndexOrThrow("CLI_COMPLEMENTO");
                    int columnBairro = cursor.getColumnIndexOrThrow("CLI_BAIRRO");
                    int columnCep = cursor.getColumnIndexOrThrow("CLI_CEP");
                    int columnCodMunicipio = cursor.getColumnIndexOrThrow("CLI_CODIGOMUNICIPIO");
                    int columnEmail = cursor.getColumnIndexOrThrow("CLI_EMAIL");
                    int columnTel = cursor.getColumnIndexOrThrow("CLI_TELEFONE");
                    int columnDtCadastro = cursor.getColumnIndexOrThrow("CLI_DATACADASTRO");

                    while (cursor.moveToNext()) {
                        String id = cursor.getString(columnCod);
                        String razaoSocial = cursor.getString(columnRazaoSocial);
                        String nomeFantasia = cursor.getString(columnNomeFantasia);
                        String cnpjCpf = cursor.getString(columnCpf);
                        String endereco = cursor.getString(columnEndereco);
                        String numero = cursor.getString(columnNumero);
                        String complemento = cursor.getString(columnComplemento);
                        String bairro = cursor.getString(columnBairro);
                        String cep = cursor.getString(columnCep);
                        String codMunicipio = cursor.getString(columnCodMunicipio);
                        String email = cursor.getString(columnEmail);
                        String tel = cursor.getString(columnTel);
                        String dtCadastro = cursor.getString(columnDtCadastro);

                        Clients client = new Clients(id, razaoSocial, nomeFantasia, cnpjCpf, endereco,
                                numero, complemento, bairro, cep, codMunicipio, email, tel, dtCadastro);

                        clientList.add(client);
                    }

                }
                cursor.close();
                emitter.onNext(clientList);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }:
        });
    }
}

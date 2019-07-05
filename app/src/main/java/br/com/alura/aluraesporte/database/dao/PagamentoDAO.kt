package br.com.alura.aluraesporte.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.alura.aluraesporte.model.Pagamento

@Dao
interface PagamentoDAO {

    @Insert
    fun salva(pagamento: Pagamento) : Long

    @Query("SELECT * FROM Pagamento")
    fun todos(): LiveData<List<Pagamento>>

}
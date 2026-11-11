package etec.crud.service

import etec.crud.model.Instrumento
import org.springframework.stereotype.Service

@Service
class InstrumentoServicio {

    private val instrumentos = mutableListOf<Instrumento>()

    fun guardar(instrumento: Instrumento): Instrumento {
        instrumentos.add(instrumento)
        return instrumento
    }

    fun listarTodos(): List<Instrumento> {
        return instrumentos.toList()
    }

    fun buscarPorId(id: Long): Instrumento? {
        return instrumentos.find { it.id == id }
    }

    fun actualizar(id: Long, instrumentoActualizado: Instrumento): Instrumento? {
        val indice = instrumentos.indexOfFirst { it.id == id }

        if (indice == -1) {
            return null
        }

        instrumentos[indice] = instrumentoActualizado
        return instrumentoActualizado
    }

    fun eliminar(id: Long): Boolean {
        val indice = instrumentos.indexOfFirst { it.id == id }

        if (indice == -1) {
            return false
        }

        instrumentos.removeAt(indice)
        return true
    }
}
package etec.crud.controller

import etec.crud.model.Instrumento
import etec.crud.service.InstrumentoServicio
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/instrumentos")
class InstrumentoControlador @Autowired constructor(
    private val instrumentoServicio: InstrumentoServicio
) {

    @GetMapping
    fun listarTodos(): ResponseEntity<*> {
        return ResponseEntity.ok(instrumentoServicio.listarTodos())
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<*> {
        val instrumento = instrumentoServicio.buscarPorId(id)
            ?: return ResponseEntity.notFound().build<Any>()

        return ResponseEntity.ok(instrumento)
    }

    @PostMapping
    fun crear(@RequestBody instrumento: Instrumento): ResponseEntity<*> {
        if (instrumento.nombre.isBlank()) {
            return ResponseEntity.badRequest().build<Any>()
        }

        val guardado = instrumentoServicio.guardar(instrumento)
        return ResponseEntity.status(201).body(guardado)
    }

    @PutMapping("/{id}")
    fun actualizar(
        @PathVariable id: Long,
        @RequestBody instrumento: Instrumento
    ): ResponseEntity<*> {
        if (instrumento.nombre.isBlank()) {
            return ResponseEntity.badRequest().build<Any>()
        }

        val actualizado = instrumentoServicio.actualizar(id, instrumento)
            ?: return ResponseEntity.notFound().build<Any>()

        return ResponseEntity.ok(actualizado)
    }

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Long): ResponseEntity<*> {
        return if (instrumentoServicio.eliminar(id)) {
            ResponseEntity.noContent().build<Any>()
        } else {
            ResponseEntity.notFound().build<Any>()
        }
    }
}
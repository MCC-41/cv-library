/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers.icontrollers;

import com.mii.cvlibrary.models.data.ResponseList;
import com.mii.cvlibrary.models.data.ResponseRest;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author habib
 * @param <L> response list
 * @param <T> response rest
 * @param <E> model or entity
 * @param <I> datatype
 */
public interface IController<E,I> {
    ResponseList<E> getAll();
    ResponseRest<E> getById(@PathVariable I id);
    ResponseRest<E> insert(@RequestBody E data);
    ResponseRest<E> update(@PathVariable I id,@RequestBody E data);
    ResponseRest<E> delete(@PathVariable I id);
}

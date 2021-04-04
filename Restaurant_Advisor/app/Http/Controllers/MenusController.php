<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Menu;
use Illuminate\Support\Facades\Validator;

class MenusController extends Controller
{
    function getAll(){
        return response()->json((menu::all()), 200);
    }
    function getAllOf($id){
        return response()->json((menu::where('restaurant_id',$id)->get()), 200);
    }
    function getById($id){
        return response()->json((menu::findOrfail($id)), 200);
    }
    function create($id,Request $request){
        $validator = Validator::make($request->all(), [
            'name' => 'required',
            'description' => 'required',
            'price' => 'required'
        ]);

        if ($validator->fails()) {
            return response()->json([
                'message' => "A fiels is missing"
            ],400);
        } else {
            $menu = new Menu();

            $menu->name = $request->name;
            $menu->description = $request->description;
            $menu->price = $request->price;
            $menu->restaurant_id = $id;

            $menu->save();

            return response($menu, 201);
        }
    }
    function delete(Request $request, $id){
        $menu = Menu::findOrfail($id);

        if ($menu){
            $menu->delete();
        } else {
            return response()->json([
                'message' => "menu doesn't exist."
            ],400);
        }
    }
    function update(Request $request, $id){
        $menu = Menu::findOrfail($id);

        if ($menu){
            if ($request->name)
                $menu->name = $request->name;
            if ($request->description)
                $menu->description = $request->description;
            if ($request->price)
                $menu->price = $request->price;

            $menu->save();
        } else {
            return response()->json([
                'message' => "menu doesn't exist."
            ],400);
        }
    }
}

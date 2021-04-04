<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});
// user
Route::post('/register', [App\Http\Controllers\UsersController::class, 'create']);
Route::post('/auth',[App\Http\Controllers\UsersController::class,'auth']);
Route::get('/users', [App\Http\Controllers\UsersController::class, 'getAll']);

// restaurant
Route::get('/restaurants', [App\Http\Controllers\RestaurantsController::class, 'getALL']);
Route::get('/restaurants/{id}', [App\Http\Controllers\RestaurantsController::class, 'getById']);
Route::post('/restaurants', [App\Http\Controllers\RestaurantsController::class, 'create']);
Route::put('/restaurant/{id}', [App\Http\Controllers\RestaurantsController::class, 'updateRestaurant']);
Route::delete('/restaurant/{id}', [App\Http\Controllers\RestaurantsController::class, 'delete']);

// menu
Route::get('/menus', [App\Http\Controllers\MenusController::class, 'getAll']);
Route::get('/menus/{id}', [App\Http\Controllers\MenusController::class, 'getById']);
Route::get('/restaurants/{id}/menus', [App\Http\Controllers\MenusController::class, 'getAllOf']);
Route::post('/restaurants/{id}/menus', [App\Http\Controllers\MenusController::class, 'create']);
Route::put('/menus/{id}', [App\Http\Controllers\MenusController::class, 'update']);
Route::delete('/menus/{id}', [App\Http\Controllers\MenusController::class, 'delete']);

# Groupe de nouar_a & rafali_h

## Mise en place de Database chez vous:

* environnement laravel:
```
emacs .env
```
modifier la ligne de DB avec le nom de votre DB dans mysql

* commande de migration:
```
php artisan migrate --seed
```

* lancement du serveur:
```
php artisan serve
```

## Documentation:

### Migrations

#### Les utilisateurs:

```php
Schema::create('users', function (Blueprint $table) {
    $table->id();
    $table->string('username')->unique();
    $table->string('name');
    $table->string('firstname');
    $table->string('email')->unique();
    $table->timestamp('email_verified_at')->nullable();
    $table->integer('age');
    $table->string('password');
    $table->rememberToken();
    $table->timestamps();
});
```
Vous retrouverez le model des utilisateurs dans le fichier ```Restaurant_Advisor/app/Models/User.php```.

#### Les restaurants:

```php
Schema::create('restaurants', function (Blueprint $table) {
    $table->id();
    $table->string('name');
    $table->string('description');
    $table->string('localization');
    $table->float('grade');
    $table->string('phone_number');
    $table->string('website')->nullable();
    $table->string('hours');
    $table->timestamps();
    $table->softDeletes();
});
```
Vous retrouverez le model des restaurants dans le fichier ```Restaurant_Advisor/app/Models/Restaurant.php```.

#### Les menus:

```php
Schema::create('menus', function (Blueprint $table) {
    $table->id();
    $table->string('name');
    $table->longText('description');
    $table->float('price');
    $table->integer('restaurant_id');
    $table->timestamps();
    $table->softDeletes();
});
```
Vous retrouverez le model des menus dans le fichier ```Restaurant_Advisor/app/Models/Menu.php```.

### Routes

#### Les utilisateurs:

* Enregistrement simple d'un nouvel utilisateur dans la base de donnée si les informations sont correctes, via la route:
```php
Route::post('/register', [App\Http\Controllers\UsersController::class, 'create']);
```
* Aunthentification d'un utilisateur par email ou par username, via la route:
```php
Route::post('/auth',[App\Http\Controllers\UsersController::class,'auth']);
```
* Affichage de tous les utilisateurs enregistrés dans la base de donnée, via la route:
```php
Route::get('/users', [App\Http\Controllers\UsersController::class, 'getAll']);
```
Vous retrouverez le controller des utilisateurs dans le fichier ```Restaurant_Advisor/app/Http/Controllers/UsersController.php```.

#### Les restaurants:

* Affichage de tous les restaurants enregistrés dans la base de donnée, via la route:
```php
Route::get('/restaurants', [App\Http\Controllers\RestaurantsController::class, 'getALL']);
```
* Affichage d'un restaurant spécifique enregistré dans la base de donnée, via la route:
```php
Route::get('/restaurants/{id}', [App\Http\Controllers\RestaurantsController::class, 'getById']);
```
* Enregistrement simple d'un nouveau restaurant dans la base de donnée si les informations sont correctes, via la route:
```php
Route::post('/restaurants', [App\Http\Controllers\RestaurantsController::class, 'create']);
```
* Mise à jour d'un restaurant spécifique dans la base de donnée, via la route:
```php
Route::put('/restaurant/{id}', [App\Http\Controllers\RestaurantsController::class, 'update']);
```
* Suppression soft* d'un restaurant spécifique de la base de donnée, via la route:
```php
Route::delete('/restaurant/{id}', [App\Http\Controllers\RestaurantsController::class, 'delete']);
```
Vous retrouverez le controller des restaurants dans le fichier ```Restaurant_Advisor/app/Http/Controllers/RestaurantsController.php```.

#### les menus:

* Affichage de tout les menus enregistrés dans la base de donnée, via la route:
```php
Route::get('/menus', [App\Http\Controllers\MenusController::class, 'getAll']);
```
* Affichage d'un menu spécifique enregistré dans la base de donnée, via la route:
```php
Route::get('/menus/{id}', [App\Http\Controllers\MenusController::class, 'getById']);
```
* Affichage de tout les menus d'un restaurant enregistrés dans la base de donnée, via la route:
```php
Route::get('/restaurants/{id}/menus', [App\Http\Controllers\MenusController::class, 'getAllOf']);
```
* Enregistrement simple d'un nouveau menu dans la base de donnée si les informations sont correctes, via la route:
```php
Route::post('/restaurants/{id}/menus', [App\Http\Controllers\MenusController::class, 'create']);
```
* Mise à jour d'un menu spécifique dans la base de donnée, via la route:
```php
Route::put('/menus/{id}', [App\Http\Controllers\MenusController::class, 'update']);
```
* Suppression soft* d'un menu spécifique de la base de donnée, via la route:
```php
Route::delete('/menus/{id}', [App\Http\Controllers\MenusController::class, 'delete']);
```
Vous retrouverez le controller des menus dans le fichier ```Restaurant_Advisor/app/Http/Controllers/MenusController.php```.


_Suppression soft*: toujours existant mais non visible._ <br>
[README2](README2.md)

## Auteurs

[Sid-Ahmed NOUAR Linkedin](https://www.linkedin.com/in/sid-ahmed-nouar-4347b5159/)

[Hary Rafalimanana Linkedin](https://www.linkedin.com/in/hary-rafalimanana-776333203/)

## Version

* 1.0
    * Version initial

## License

Ce projet est en opensource, lire le CGG de ETNA school Paris.B

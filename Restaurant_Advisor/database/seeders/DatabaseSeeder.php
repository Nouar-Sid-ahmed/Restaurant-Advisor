<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use App\Models\User;
use App\Models\Menu;
use App\Models\Restaurant;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run()
    {
        User::create([
            'id'=>'1',
            'username'=>'Fullmetal',
            'email'=>'edward.elric@amestris.com',
            'name'=>'Elric',
            'firstname'=>'Edward',
            'age'=>'19',
            'password'=>bcrypt('password'),
        ]);
        Restaurant::create([
            "id" => 1,
            "name" => "MacDonald's",
            "description" => "Classic, long-running fast-food chain known for its burgers, fries & shakes.",
            "grade" => 3.2,
            "localization" => "Centre Commercial Grand Ciel, 30 Boulevard Paul Vaillant Couturier, 94200 Ivry-sur-Seine",
            "phone_number" => "01 49 60 62 60",
            "website" => "macdonalds.fr",
            "hours" => "Monday-Saturday 9AM–9PM, Sunday Closed",
        ]);
        Menu::create([
            "id" => 1,
            "name" => "Menu A5",
            "description" => "8 sushis, 4 makis, 4 calofornia rolls",
            "price" => 16.5,
            "restaurant_id" => 1,
        ]);
    }
}

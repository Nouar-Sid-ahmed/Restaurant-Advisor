<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Restaurant;
use Illuminate\Support\Facades\Validator;

use function Ramsey\Uuid\v1;

class RestaurantsController extends Controller {
    function getAll() {
        return response()->json((restaurant::all()), 200);
    }
    function getById($id) {
        return response()->json((restaurant::findOrfail($id)), 200);
    }
    function create(Request $request){
        $validator = Validator::make($request->all(), [
            'name' => 'required',
            'description' => 'required',
            'grade' => 'required',
            'localization' => 'required',
            'phone_number' => 'required',
            'website' => 'nullable',
            'hours' => 'required'
        ]);
        if ($validator->fails()) {
            return response()->json([
                'message' => "A fiels is missing"
            ],400);
        } else {
            $restaurant = new Restaurant();

            $restaurant->name = $request->name;
            $restaurant->description = $request->description;
            $restaurant->grade = $request->grade;
            $restaurant->localization = $request->localization;
            $restaurant->phone_number = $request->phone_number;
            $restaurant->website = $request->website;
            $restaurant->hours = $request->hours;

            $restaurant->save();

            return response($restaurant, 201);
        }
    }
    function delete(Request $request, $id){
        $restaurant = Restaurant::findOrfail($id);

        if ($restaurant){
            $restaurant->delete();
            return response()->json([
                'message' => "Restaurant successfully deleted."
            ],200);
        } else {
            return response()->json([
                'message' => "Restaurant doesn't exist."
            ],400);
        }
    }
    function updateRestaurant(Request $request, $id){
        $restaurant = Restaurant::find($id);
        $validator = Validator::make($request->all(), [
            'name' => 'nullable',
            'description' => 'nullable',
            'grade' => 'nullable',
            'localization' => 'nullable',
            'phone_number' => 'nullable',
            'website' => 'nullable',
            'hours' => 'nullable'
        ]);
        if ($validator->fails()) {
            return response()->json([
                'message' => $validator->errors()
            ], 400);
        }
        else if ($restaurant) {
            if ($request->name)
                $restaurant-> name = $request->name;
            if ($request->description)
                $restaurant-> description = $request->description;
            if ($request->grade)
                $restaurant-> grade = $request->grade;
            if ($request->localization)
                $restaurant-> localization = $request->localization;
            if ($request->phone_number)
                $restaurant-> phone_number = $request->phone_number;
            if ($request->website)
                $restaurant-> website = $request->website;
            if ($request->hours)
                $restaurant-> hours = $request->hours;
            $restaurant->save();
            return response($restaurant,200);;
        } else {
            return response()->json([
                'message' => "Restaurant doesn't exist."
            ], 400);
        }
    }
}

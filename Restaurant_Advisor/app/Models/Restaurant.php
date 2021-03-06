<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Restaurant extends Model
{
    use HasFactory;

    protected $fillable = [
        'name',
        'description',
        'grade',
        'localization',
        'phone_number',
        'website',
        'hours',
    ];

    use SoftDeletes;
}
